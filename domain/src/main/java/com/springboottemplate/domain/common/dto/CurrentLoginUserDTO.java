package com.springboottemplate.domain.common.dto;


import com.springboottemplate.domain.system.user.dto.UserDTO;
import java.util.Set;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class CurrentLoginUserDTO {

    private UserDTO userInfo;
    private String roleKey;
    private Set<String> permissions;


}
