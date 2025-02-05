package com.springboottemplate.domain.system.role.command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sleepyhead
 */
@Data
@NoArgsConstructor
public class UpdateStatusCommand {

    private Long roleId;

    private Integer status;

}
