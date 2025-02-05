package com.springboottemplate.domain.system.user.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserCommand extends AddUserCommand {

    private Long userId;

}
