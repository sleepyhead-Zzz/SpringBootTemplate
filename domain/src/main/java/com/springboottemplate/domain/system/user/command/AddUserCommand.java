package com.springboottemplate.domain.system.user.command;

import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class AddUserCommand {

    private Long deptId;

    private String username;

    private String nickname;

    private String email;

    private String phoneNumber;

    private Integer sex;

    private String avatar;

    private String password;

    private Integer status;

    private Long roleId;

    private Long postId;

    private String remark;


}
