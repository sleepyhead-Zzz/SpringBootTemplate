package com.springboottemplate.domain.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode;
import com.springboottemplate.domain.system.dept.model.DeptModelFactory;
import com.springboottemplate.domain.system.user.command.AddUserCommand;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.domain.system.user.db.SysUserService;
import com.springboottemplate.infrastructure.user.AuthenticationUtils;
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

    private DeptModelFactory deptModelFactory;

    public UserModel(SysUserEntity entity, SysUserService userService, DeptModelFactory deptModelFactory) {
        this(userService, deptModelFactory);

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public UserModel(SysUserService userService, DeptModelFactory deptModelFactory) {
        this.userService = userService;
        this.deptModelFactory = deptModelFactory;

    }

    public void loadAddUserCommand(AddUserCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "userId");
        }
    }

    public void checkUsernameIsUnique() {
        if (userService.isUserNameDuplicated(getUsername())) {
            throw new ApiException(ErrorCode.Business.USER_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkPhoneNumberIsUnique() {
        if (StrUtil.isNotEmpty(getPhoneNumber()) && userService.isPhoneDuplicated(getPhoneNumber(),
            getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    public void checkEmailIsUnique() {
        if (StrUtil.isNotEmpty(getEmail()) && userService.isEmailDuplicated(getEmail(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_EMAIL_IS_NOT_UNIQUE);
        }
    }

    public void resetPassword(String newPassword) {
        setPassword(AuthenticationUtils.encryptPassword(newPassword));
    }

    public void checkFieldRelatedEntityExist() {

        if (getDeptId() != null) {
            deptModelFactory.loadById(getDeptId());
        }
    }
}
