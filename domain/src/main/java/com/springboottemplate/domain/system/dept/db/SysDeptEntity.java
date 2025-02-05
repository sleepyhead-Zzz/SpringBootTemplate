package com.springboottemplate.domain.system.dept.db;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboottemplate.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author Sleepyhead
 * @since 2022-10-02
 */
@Getter
@Setter
@TableName("sys_dept")
@Schema(description = "部门表")
public class SysDeptEntity extends BaseEntity<SysDeptEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "部门id")
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @Schema(description = "父部门id")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "祖级列表")
    @TableField("ancestors")
    private String ancestors;

    @Schema(description = "部门名称")
    @TableField("dept_name")
    private String deptName;

    @Schema(description = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @TableField("leader_id")
    private Long leaderId;

    @Schema(description = "负责人")
    @TableField("leader_name")
    private String leaderName;

    @Schema(description = "联系电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "部门状态（0正常 1停用）")
    @TableField("`status`")
    private Integer status;


    @Override
    public Serializable pkVal() {
        return this.deptId;
    }

}
