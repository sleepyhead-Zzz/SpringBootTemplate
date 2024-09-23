package com.springboottemplate.admin.customize.service.permission;

import cn.hutool.extra.spring.SpringUtil;
import com.springboottemplate.admin.customize.service.permission.model.AbstractDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.AllDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.CustomDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.DefaultDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.DeptTreeDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.OnlySelfDataPermissionChecker;
import com.springboottemplate.admin.customize.service.permission.model.checker.SingleDeptDataPermissionChecker;
import com.springboottemplate.domain.system.dept.db.SysDeptService;
import com.springboottemplate.infrastructure.user.web.DataScopeEnum;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * 数据权限检测器工厂
 *
 * @author valarchie
 */
@Component
public class DataPermissionCheckerFactory {

    private static AbstractDataPermissionChecker allChecker;
    private static AbstractDataPermissionChecker customChecker;
    private static AbstractDataPermissionChecker singleDeptChecker;
    private static AbstractDataPermissionChecker deptTreeChecker;
    private static AbstractDataPermissionChecker onlySelfChecker;
    private static AbstractDataPermissionChecker defaultSelfChecker;


    @PostConstruct
    public void initAllChecker() {
        SysDeptService deptService = SpringUtil.getBean(SysDeptService.class);

        allChecker = new AllDataPermissionChecker();
        customChecker = new CustomDataPermissionChecker(deptService);
        singleDeptChecker = new SingleDeptDataPermissionChecker(deptService);
        deptTreeChecker = new DeptTreeDataPermissionChecker(deptService);
        onlySelfChecker = new OnlySelfDataPermissionChecker(deptService);
        defaultSelfChecker = new DefaultDataPermissionChecker();
    }


    public static AbstractDataPermissionChecker getChecker(SystemLoginUser loginUser) {
        if (loginUser == null) {
            return deptTreeChecker;
        }

        DataScopeEnum dataScope = loginUser.getRoleInfo().getDataScope();
        return switch (dataScope) {
            case ALL -> allChecker;
            case CUSTOM_DEFINE -> customChecker;
            case SINGLE_DEPT -> singleDeptChecker;
            case DEPT_TREE -> deptTreeChecker;
            case ONLY_SELF -> onlySelfChecker;
            default -> defaultSelfChecker;
        };
    }

}
