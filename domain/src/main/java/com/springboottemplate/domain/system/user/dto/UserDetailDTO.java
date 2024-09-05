package com.springboottemplate.domain.system.user.dto;

import java.util.Set;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserDetailDTO {

    private UserDTO user;


    private Long postId;

    private Long roleId;

    private Set<String> permissions;

}
