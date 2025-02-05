package com.springboottemplate.domain.system.role.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottemplate.common.core.page.AbstractPageQuery;
import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleQuery extends AbstractPageQuery<SysRoleEntity> {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色权限字符串")
    private String roleKey;

    @Schema(description = "角色状态")
    private String status;


    @Override
    public QueryWrapper<SysRoleEntity> addQueryCondition() {
        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<SysRoleEntity>()
            .eq(status != null, "status", status)
            .eq(StrUtil.isNotEmpty(roleKey), "role_key", roleKey)
            .like(StrUtil.isNotEmpty(roleName), "role_name", roleName);

//        this.addTimeCondition(queryWrapper, "create_time");

//        this.setOrderColumn("role_sort");
//        this.addSortCondition(queryWrapper);

        return queryWrapper;
    }
}
