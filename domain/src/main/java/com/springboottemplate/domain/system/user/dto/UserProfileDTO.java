package com.springboottemplate.domain.system.user.dto;


import com.springboottemplate.domain.system.post.db.SysPostEntity;
import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class UserProfileDTO {


    public UserProfileDTO(SysUserEntity userEntity, SysPostEntity postEntity, SysRoleEntity roleEntity) {
        if (userEntity != null) {
            this.user = new UserDTO(userEntity);
        }

        if (postEntity != null) {
            this.postName = postEntity.getPostName();
        }

        if (roleEntity != null) {
            this.roleName = roleEntity.getRoleName();
        }
    }

    private UserDTO user;
    private String roleName;
    private String postName;
}
