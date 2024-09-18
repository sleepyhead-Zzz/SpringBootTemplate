package com.springboottemplate.domain.issue.repair.command;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateRepairCommand extends AddRepairCommand {

    private Long repairId;

}
