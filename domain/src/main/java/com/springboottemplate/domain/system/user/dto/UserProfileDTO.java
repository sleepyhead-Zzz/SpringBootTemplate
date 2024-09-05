package com.springboottemplate.domain.system.user.dto;


import com.springboottemplate.domain.system.user.db.SysUserEntity;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserProfileDTO {

    public UserProfileDTO(SysUserEntity userEntity) {
        if (userEntity != null) {
            this.user = new UserDTO(userEntity);
        }


    }

    private UserDTO user;
    private String roleName;
    private String postName;

}
