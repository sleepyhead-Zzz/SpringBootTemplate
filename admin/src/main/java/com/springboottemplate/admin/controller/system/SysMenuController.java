package com.springboottemplate.admin.controller.system;

import cn.hutool.core.lang.tree.Tree;
import com.springboottemplate.admin.customize.aop.accessLog.AccessLog;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.common.enums.common.BusinessTypeEnum;
import com.springboottemplate.domain.system.menu.MenuApplicationService;
import com.springboottemplate.domain.system.menu.command.AddMenuCommand;
import com.springboottemplate.domain.system.menu.command.UpdateMenuCommand;
import com.springboottemplate.domain.system.menu.dto.MenuDTO;
import com.springboottemplate.domain.system.menu.dto.MenuDetailDTO;
import com.springboottemplate.domain.system.menu.query.MenuQuery;
import com.springboottemplate.infrastructure.user.AuthenticationUtils;
import com.springboottemplate.infrastructure.user.web.SystemLoginUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "菜单API", description = "菜单相关的增删查改")
@RestController
@RequestMapping("/system/menu")
@Validated
@RequiredArgsConstructor
public class SysMenuController {

    private final MenuApplicationService menuApplicationService;

    /**
     * 获取菜单列表
     */
    @Operation(summary = "菜单列表")
    @PreAuthorize("@permission.has('system:menu:list')")
    @PostMapping("list")
    public ResponseDTO<List<MenuDTO>> listMenu(@ParameterObject @ModelAttribute MenuQuery menuQuery) {
        List<MenuDTO> menuList = menuApplicationService.getMenuList(menuQuery);
        return ResponseDTO.ok(menuList);
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @Operation(summary = "菜单详情")
    @PreAuthorize("@permission.has('system:menu:query')")
    @GetMapping(value = "/{menuId}")
    public ResponseDTO<MenuDetailDTO> menuInfo(@PathVariable @NotNull @PositiveOrZero Long menuId) {
        MenuDetailDTO menu = menuApplicationService.getMenuInfo(menuId);
        return ResponseDTO.ok(menu);
    }

    /**
     * 获取菜单下拉树列表
     */
    @Operation(summary = "菜单列表（树级）", description = "菜单树级下拉框")
    @GetMapping("/dropdown")
    public ResponseDTO<List<Tree<Long>>> dropdownMenuList() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        List<Tree<Long>> dropdownList = menuApplicationService.getDropdownList(loginUser);
        // 如果 dropdownList 为 null，则返回一个空列表
        return ResponseDTO.ok(Objects.requireNonNullElse(dropdownList, Collections.emptyList()));
    }


    /**
     * 新增菜单 需支持一级菜单以及 多级菜单 子菜单为一个 或者 多个的情况 隐藏菜单不显示  以及rank排序 内链 和 外链
     */
    @Operation(summary = "添加菜单")
    @PreAuthorize("@permission.has('system:menu:add')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> addMenu(@RequestBody AddMenuCommand addCommand) {
        menuApplicationService.addMenu(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改菜单
     */
    @Operation(summary = "编辑菜单")
    @PreAuthorize("@permission.has('system:menu:edit')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{menuId}")
    public ResponseDTO<Void> editMenu(@PathVariable("menuId") Long menuId,
        @RequestBody UpdateMenuCommand updateCommand) {
        updateCommand.setMenuId(menuId);
        menuApplicationService.updateMenu(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除菜单
     */
    @Operation(summary = "删除菜单")
    @PreAuthorize("@permission.has('system:menu:remove')")
    @AccessLog(title = "菜单管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{menuId}")
    public ResponseDTO<Void> removeMenu(@PathVariable("menuId") Long menuId) {
        menuApplicationService.remove(menuId);
        return ResponseDTO.ok();
    }
}
