package com.springboottemplate.domain.system.dept.command;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class AddDeptCommand {

    /**
     * 父部门ID
     */
    @NotNull
    @PositiveOrZero
    private Long parentId;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Size(max = 30, message = "部门名称长度不能超过30个字符")
    private String deptName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    @PositiveOrZero(message = "显示顺序必须是一个正整数")
    private Integer orderNum;

    /**
     * 负责人
     */
    @Size(max = 30, message = "领导名称长度不能超过30个字符")
    private String leaderName;

    /**
     * 联系电话
     */
    @Size(max = 13, message = "联系电话长度不能超过13个字符")
    private String phone;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    @PositiveOrZero(message = "状态必须是一个正整数")
    private Integer status;


}
