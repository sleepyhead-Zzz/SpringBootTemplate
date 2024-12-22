package com.springboottemplate.admin.customize.service.login;


import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.springboottemplate.common.constant.Constants.Token;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode.Client;
import com.springboottemplate.domain.common.cache.RedisCache;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.crypto.SecretKey;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * token验证处理
 *
 * @author valarchie
 */
@Component
@Slf4j
@Data
@RequiredArgsConstructor
public class TokenService {

    /**
     * 自定义令牌标识
     */
    @Value("${token.header}")
    private String header;
    /**
     * 令牌秘钥
     */
    @Value("${token.secret}")
    private static String SECRET = "1231233sasdadsdasasdsadasd12123312312dsadasdsadsadsadsadasdsasdadsadsahz121132123";
    /**
     * 加密算法
     */
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = SIG.HS256;

    public static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    /**
     * jwt签发者
     */
    private final static String JWT_ISS = "Sleepyhead";
    /**
     * jwt主题
     */
    private final static String SUBJECT = "Peripherals";
    /**
     * 自动刷新token的时间，当过期时间不足autoRefreshTime的值的时候，会触发刷新用户登录缓存的时间 比如这个值是20,   用户是8点登录的， 8点半缓存会过期， 当过8.10分的时候，就少于20分钟了，便触发
     * 刷新登录用户的缓存时间
     */
    @Value("${token.autoRefreshTime}")
    private long autoRefreshTime;

    private final RedisCache redisCache;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public SystemLoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求携带的令牌
        String token = getTokenFromRequest(request);
        if (StrUtil.isNotEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                // 解析对应的权限以及用户信息
                String uuid = (String) claims.get(Token.LOGIN_USER_KEY);

                SystemLoginUser objectOnlyInCacheById = redisCache.loginUserCache.getObjectOnlyInCacheById(uuid);
                if (objectOnlyInCacheById == null) {
                    throw new ApiException(Client.INVALID_TOKEN);
                }
                return objectOnlyInCacheById;
            } catch (SignatureException | MalformedJwtException | UnsupportedJwtException |
                     IllegalArgumentException jwtException) {
                log.error("parse token failed.", jwtException);
                throw new ApiException(jwtException, Client.INVALID_TOKEN);
            } catch (Exception e) {
                log.error("fail to get cached user from redis", e);
                throw new ApiException(e, Client.TOKEN_PROCESS_FAILED, e.getMessage());
            }

        }
        return null;
    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createTokenAndPutUserInCache(SystemLoginUser loginUser) {
        loginUser.setCachedKey(IdUtil.fastUUID());

        redisCache.loginUserCache.set(loginUser.getCachedKey(), loginUser);

        return generateToken(MapUtil.of(Token.LOGIN_USER_KEY, loginUser.getCachedKey()));
    }

    /**
     * 当超过20分钟，自动刷新token
     *
     * @param loginUser 登录用户
     */
    public void refreshToken(SystemLoginUser loginUser) {
        long currentTime = System.currentTimeMillis();
        if (currentTime > loginUser.getAutoRefreshCacheTime()) {
            loginUser.setAutoRefreshCacheTime(currentTime + TimeUnit.MINUTES.toMillis(autoRefreshTime));
            // 根据uuid将loginUser存入缓存
            redisCache.loginUserCache.set(loginUser.getCachedKey(), loginUser);
        }
    }


    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
            .header()
            .add("typ", "JWT")
            .add("alg", "HS256")
            .and()
            .claims(claims)
            // 主题
            .subject(SUBJECT)
            // 签发者
            .issuer(JWT_ISS)
            // 签名
            .signWith(KEY, ALGORITHM)
            .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }


    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    private String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        assert claims != null;
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @return token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            if (StrUtil.isNotEmpty(token) && token.startsWith(Token.PREFIX)) {
                token = StrUtil.stripIgnoreCase(token, Token.PREFIX, null);
            }
            return token;
        }
        return null;
    }

}
