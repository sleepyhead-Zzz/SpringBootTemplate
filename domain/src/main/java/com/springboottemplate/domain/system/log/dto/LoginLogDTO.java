package com.springboottemplate.domain.system.log.dto;

import com.springboottemplate.common.enums.BasicEnumUtil;
import com.springboottemplate.common.enums.common.LoginStatusEnum;
import com.springboottemplate.domain.system.log.db.SysLoginInfoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data

public class LoginLogDTO {

    public LoginLogDTO(SysLoginInfoEntity entity) {
        if (entity != null) {
            logId = entity.getInfoId() + "";
            username = entity.getUsername();
            ipAddress = entity.getIpAddress();
            loginLocation = entity.getLoginLocation();
            operationSystem = entity.getOperationSystem();
            browser = entity.getBrowser();
            status = entity.getStatus();
            statusStr = BasicEnumUtil.getDescriptionByValue(LoginStatusEnum.class, entity.getStatus());
            msg = entity.getMsg();
            loginTime = entity.getLoginTime();
        }
    }


    @Schema(description = "ID")
    private String logId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "ip地址")
    private String ipAddress;

    @Schema(description = "登录地点")
    private String loginLocation;

    @Schema(description = "操作系统")
    private String operationSystem;

    @Schema(description = "浏览器")
    private String browser;

    private Integer status;

    @Schema(description = "状态")
    private String statusStr;

    @Schema(description = "描述")
    private String msg;

    @Schema(description = "登录时间")
    private Date loginTime;

}
