package com.springboottemplate.domain.system.user.dto;

import com.springboottemplate.domain.system.post.dto.PostDTO;
import com.springboottemplate.domain.system.role.dto.RoleDTO;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class UserDetailDTO {

    private UserDTO user;

    /**
     * 返回所有role
     */
    private List<RoleDTO> roleOptions;

    /**
     * 返回所有posts
     */
    private List<PostDTO> postOptions;

    private Long postId;

    private Long roleId;

    private Set<String> permissions;

}
