package com.springboottemplate.domain.system.user;

import com.springboottemplate.domain.common.cache.CacheCenter;
import com.springboottemplate.domain.common.dto.CurrentLoginUserDTO;
import com.springboottemplate.domain.system.user.command.AddUserCommand;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.domain.system.user.db.SysUserService;
import com.springboottemplate.domain.system.user.dto.UserDTO;
import com.springboottemplate.domain.system.user.dto.UserProfileDTO;
import com.springboottemplate.domain.system.user.model.UserModel;
import com.springboottemplate.domain.system.user.model.UserModelFactory;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final SysUserService userService;


    private final UserModelFactory userModelFactory;

//    public PageDTO<UserDTO> getUserList(SearchUserQuery<SearchUserDO> query) {
//        Page<SearchUserDO> userPage = userService.getUserList(query);
//        List<UserDTO> userDTOList = userPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
//        return new PageDTO<>(userDTOList, userPage.getTotal());
//    }

    public UserProfileDTO getUserProfile(Long userId) {

        SysUserEntity userEntity = userService.getById(userId);

        return new UserProfileDTO(userEntity);
    }


    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息
     */
    public CurrentLoginUserDTO getLoginUserInfo(SystemLoginUser loginUser) {
        CurrentLoginUserDTO permissionDTO = new CurrentLoginUserDTO();

        permissionDTO.setUserInfo(new UserDTO(CacheCenter.userCache.getObjectById(loginUser.getUserId())));
//        permissionDTO.setRoleKey(loginUser.getRoleInfo().getRoleKey());
//        permissionDTO.setPermissions(loginUser.getRoleInfo().getMenuPermissions());

        return permissionDTO;
    }


    public void addUser(AddUserCommand command) {
        UserModel model = userModelFactory.create();
        command.setStatus(1);
        model.loadAddUserCommand(command);

        model.checkUsernameIsUnique();
        model.checkPhoneNumberIsUnique();
        model.checkEmailIsUnique();
        model.checkFieldRelatedEntityExist();
        model.resetPassword(command.getPassword());

        model.insert();
    }

}
