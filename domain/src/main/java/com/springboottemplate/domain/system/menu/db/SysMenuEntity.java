package com.springboottemplate.domain.system.menu.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.springboottemplate.common.core.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author Sleepyhead
 * @since 2023-07-21
 */
@Getter
@Setter
@TableName("sys_menu")
@Schema(description = "菜单权限表")
public class SysMenuEntity extends BaseEntity<SysMenuEntity> {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    private Long parentId;

    private String name;

    @Schema(description = "菜单的类型(1为页面 2为目录 3为iFrame 4为外部网站 5为按钮)")
    private Integer menuType;

    private String component;

    private String path;

    private String redirect;

    private String meta;

    private String permission;

    private String remark;

    private Boolean status;

    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
