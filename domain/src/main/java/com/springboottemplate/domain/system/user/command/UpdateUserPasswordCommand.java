package com.springboottemplate.domain.system.user.command;

import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class UpdateUserPasswordCommand {

    private Long userId;
    private String newPassword;
    private String oldPassword;

}
