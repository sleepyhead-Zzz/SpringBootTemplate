package com.springboottemplate.domain.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.domain.system.user.db.SysUserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class UserModel extends SysUserEntity {

    private SysUserService userService;

    public UserModel(SysUserEntity entity, SysUserService userService) {
        this(userService);

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public UserModel(SysUserService userService) {
        this.userService = userService;

    }
}
