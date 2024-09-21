package com.springboottemplate.domain.issue.repair.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddRepairCommand {


    private String deviceType;

    @Schema(description = "报修人")
    private String reporter;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "问题描述")
    private String issueDescription;

    @Schema(description = "位置")
    private String location;


    private Long deptId;
}
