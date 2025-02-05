package com.springboottemplate.admin.customize.service.permission.model.checker;

import com.springboottemplate.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.DataCondition;
import com.springboottemplate.domain.system.dept.db.SysDeptService;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据权限测试接口
 *
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AllDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;


    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        return true;
    }
}
