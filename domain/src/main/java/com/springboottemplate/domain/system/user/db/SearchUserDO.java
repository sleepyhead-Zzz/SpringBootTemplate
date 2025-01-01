package com.springboottemplate.domain.system.user.db;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 如果Entity的字段和复杂查询不匹配时   自定义类来接收
 *
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchUserDO extends SysUserEntity {

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "部门领导")
    private String deptLeader;

}
