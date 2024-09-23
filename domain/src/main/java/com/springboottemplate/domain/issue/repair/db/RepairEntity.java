package com.springboottemplate.domain.issue.repair.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboottemplate.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName repair
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "repair")
@Data
@Schema(description = "报修表")
public class RepairEntity extends BaseEntity<RepairEntity> {

    @TableField
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

    @Override
    public Serializable pkVal() {
        return this.repairId;
    }

}