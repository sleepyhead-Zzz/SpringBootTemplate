package com.springboottemplate.domain.system.role.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottemplate.common.core.page.AbstractPageQuery;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnallocatedRoleQuery extends AbstractPageQuery<SysUserEntity> {

    private Long roleId;
    private String username;
    private String phoneNumber;

    public QueryWrapper<SysUserEntity> addQueryCondition() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(username), "u.username", username)
            .like(StrUtil.isNotEmpty(phoneNumber), "u.phone_number", phoneNumber)
            .and(o -> o.ne("r.role_id", roleId)
                .or().isNull("u.role_id")
                .or().eq("u.role_id", 0));

        return queryWrapper;
    }

}
