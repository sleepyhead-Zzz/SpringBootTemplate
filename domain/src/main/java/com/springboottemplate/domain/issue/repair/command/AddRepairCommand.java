package com.springboottemplate.domain.issue.repair.command;

import lombok.Data;

@Data
public class AddRepairCommand {


    private String deviceType;


    private String reporter;


    private String deviceName;


    private String issueDescription;


    private String location;


    private Long deptId;
}
