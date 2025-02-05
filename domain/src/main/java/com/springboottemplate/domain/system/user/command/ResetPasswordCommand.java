package com.springboottemplate.domain.system.user.command;

import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class ResetPasswordCommand {

    private Long userId;
    private String password;

}
