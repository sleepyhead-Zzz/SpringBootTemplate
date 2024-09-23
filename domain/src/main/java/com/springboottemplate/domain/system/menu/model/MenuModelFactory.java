package com.springboottemplate.domain.system.menu.model;

import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode;
import com.springboottemplate.domain.system.menu.db.SysMenuEntity;
import com.springboottemplate.domain.system.menu.db.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 菜单模型工厂
 *
 * @author valarchie
 */
@Component
@RequiredArgsConstructor
public class MenuModelFactory {

    private final SysMenuService menuService;

    public MenuModel loadById(Long menuId) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId, menuService);
    }

    public MenuModel create() {
        return new MenuModel(menuService);
    }


}
