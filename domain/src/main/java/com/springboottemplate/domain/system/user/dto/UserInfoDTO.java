package com.springboottemplate.domain.system.user.dto;


import com.springboottemplate.domain.system.role.dto.RoleDTO;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class UserInfoDTO {

    private UserDTO user;
    private RoleDTO role;


}
