package com.springboottemplate.domain.issue.repair.model;

import cn.hutool.core.bean.BeanUtil;
import com.springboottemplate.domain.issue.repair.command.AddRepairCommand;
import com.springboottemplate.domain.issue.repair.command.UpdateRepairCommand;
import com.springboottemplate.domain.issue.repair.db.RepairEntity;
import com.springboottemplate.domain.issue.repair.db.RepairService;

public class RepairModel extends RepairEntity {

    private final RepairService repairService;


    public RepairModel(RepairService repairService) {
        this.repairService = repairService;
    }

    public RepairModel(RepairEntity entity, RepairService repairService) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
        this.repairService = repairService;
    }

    public void loadAddCommand(AddRepairCommand command) {
        this.setDeptId(command.getDeptId());
        this.setDeviceName(command.getDeviceName());
        this.setLocation(command.getLocation());
        this.setReporter(command.getReporter());
        this.setIssueDescription(command.getIssueDescription());
        this.setDeviceType(command.getDeviceType());
    }

    public void loadUpdateCommand(UpdateRepairCommand updateCommand) {
        loadAddCommand(updateCommand);

    }
}
