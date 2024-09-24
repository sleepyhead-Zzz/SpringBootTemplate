package com.springboottemplate.domain.system.log.dto;

import cn.hutool.core.bean.BeanUtil;
import com.springboottemplate.common.enums.BasicEnumUtil;
import com.springboottemplate.common.enums.common.BusinessTypeEnum;
import com.springboottemplate.common.enums.common.OperationStatusEnum;
import com.springboottemplate.common.enums.common.OperatorTypeEnum;
import com.springboottemplate.common.enums.common.RequestMethodEnum;
import com.springboottemplate.domain.system.log.db.SysOperationLogEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
@Schema(description = "操作日志")
public class OperationLogDTO {

    public OperationLogDTO(SysOperationLogEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
            this.requestMethod = BasicEnumUtil.getDescriptionByValue(RequestMethodEnum.class,
                entity.getRequestMethod());
            this.statusStr = BasicEnumUtil.getDescriptionByValue(OperationStatusEnum.class, entity.getStatus());
            businessTypeStr = BasicEnumUtil.getDescriptionByValue(BusinessTypeEnum.class, entity.getBusinessType());
            operatorTypeStr = BasicEnumUtil.getDescriptionByValue(OperatorTypeEnum.class, entity.getOperatorType());
        }


    }

    @Schema(description = "ID")
    private Long operationId;

    private Integer businessType;

    @Schema(description = "操作类型")
    private String businessTypeStr;

    @Schema(description = "操作类型")
    private String requestMethod;

    @Schema(description = "操作类型")
    private String requestModule;

    @Schema(description = "操作类型")
    private String requestUrl;

    @Schema(description = "操作类型")
    private String calledMethod;

    private Integer operatorType;

    @Schema(description = "操作人类型")
    private String operatorTypeStr;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "ip地址")
    private String operatorIp;

    @Schema(description = "ip地点")
    private String operatorLocation;

    @Schema(description = "部门ID")
    private Long deptId;

    @Schema(description = "部门")
    private String deptName;

    @Schema(description = "操作参数")
    private String operationParam;

    @Schema(description = "操作结果")
    private String operationResult;

    private Integer status;

    @Schema(description = "状态")
    private String statusStr;

    @Schema(description = "错误堆栈")
    private String errorStack;

    @Schema(description = "操作时间")
    private Date operationTime;

}
