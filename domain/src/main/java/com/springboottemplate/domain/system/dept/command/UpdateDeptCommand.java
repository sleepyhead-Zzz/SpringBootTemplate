package com.springboottemplate.domain.system.dept.command;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateDeptCommand extends AddDeptCommand {

    @NotNull
    @PositiveOrZero
    private Long deptId;

}
