package com.springboottemplate.domain.system.role.db;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author Sleepyhead
 * @since 2022-06-16
 */
@Primary
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

}
