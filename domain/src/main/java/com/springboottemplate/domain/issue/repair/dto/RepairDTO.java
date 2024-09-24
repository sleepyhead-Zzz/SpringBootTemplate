package com.springboottemplate.domain.issue.repair.dto;

import com.springboottemplate.domain.issue.repair.db.RepairEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RepairDTO {

    public RepairDTO(RepairEntity entity) {
        if (entity != null) {
            this.deptId = entity.getDeptId();
            this.repairId = entity.getRepairId();
            this.deviceType = entity.getDeviceType();
            this.deviceName = entity.getDeviceName();
            this.issueDescription = entity.getIssueDescription();
            this.location = entity.getLocation();
            this.reporter = entity.getReporter();
        }
    }


    @Schema(description = "报修ID")
    private Long repairId;

    @Schema(description = "报修类型")
    private String deviceType;

    @Schema(description = "报修人名")
    private String reporter;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "故障描述")
    private String issueDescription;

    @Schema(description = "位置")
    private String location;

    @Schema(description = "送修部门")
    private Long deptId;
}
