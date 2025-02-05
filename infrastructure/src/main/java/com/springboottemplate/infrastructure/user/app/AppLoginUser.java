package com.springboottemplate.infrastructure.user.app;


import com.springboottemplate.infrastructure.user.base.BaseLoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 登录用户身份权限
 *
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AppLoginUser extends BaseLoginUser {

    private static final long serialVersionUID = 1L;

    private boolean isVip;


    public AppLoginUser(Long userId, Boolean isVip, String cachedKey) {
        this.cachedKey = cachedKey;
        this.userId = userId;
        this.isVip = isVip;
    }


}
