package com.springboottemplate.admin.controller.common;

import com.springboottemplate.admin.customize.service.login.LoginService;
import com.springboottemplate.admin.customize.service.login.command.LoginCommand;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.domain.common.dto.CurrentLoginUserDTO;
import com.springboottemplate.domain.common.dto.TokenDTO;
import com.springboottemplate.domain.system.menu.MenuApplicationService;
import com.springboottemplate.domain.system.menu.dto.RouterDTO;
import com.springboottemplate.domain.system.user.UserApplicationService;
import com.springboottemplate.domain.system.user.command.AddUserCommand;
import com.springboottemplate.infrastructure.user.AuthenticationUtils;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author valarchie
 */
@Tag(name = "登录API", description = "登录相关接口")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private final UserApplicationService userApplicationService;

    private final MenuApplicationService menuApplicationService;

    /**
     * 登录方法
     *
     * @param loginCommand 登录信息
     * @return 结果
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseDTO<TokenDTO> login(@RequestBody LoginCommand loginCommand) {
        // 生成令牌
        String token = loginService.login(loginCommand);
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        CurrentLoginUserDTO currentUserDTO = userApplicationService.getLoginUserInfo(loginUser);

        return ResponseDTO.ok(new TokenDTO(token, currentUserDTO));
    }

    @Operation(summary = "获取用户对应的菜单路由", description = "用于动态生成路由")
    @GetMapping("/getRouters")
    public ResponseDTO<List<RouterDTO>> getRouters() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        List<RouterDTO> routerTree = menuApplicationService.getRouterTree(loginUser);
        return ResponseDTO.ok(routerTree);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/getLoginUserInfo")
    public ResponseDTO<CurrentLoginUserDTO> getLoginUserInfo() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        CurrentLoginUserDTO currentUserDTO = userApplicationService.getLoginUserInfo(loginUser);
        return ResponseDTO.ok(currentUserDTO);
    }


    @Operation(summary = "注册接口")
    @PostMapping("/register")
    public ResponseDTO<Void> register(@RequestBody AddUserCommand command) {
        userApplicationService.addUser(command);
        return ResponseDTO.ok();
    }

}
