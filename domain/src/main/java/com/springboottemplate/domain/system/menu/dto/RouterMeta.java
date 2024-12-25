package com.springboottemplate.domain.system.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RouterMeta {

    /**
     * 激活图标（菜单）
     */
    private String activeIcon;

    /**
     * 当前激活的菜单，有时候不想激活现有菜单，需要激活父级菜单时使用
     */
    private String activePath;

    /**
     * 是否固定标签页
     *
     * @default false
     */
    private Boolean affixTab = false;

    /**
     * 固定标签页的顺序
     *
     * @default 0
     */
    private Integer affixTabOrder = 0;

    /**
     * 需要特定的角色标识才可以访问
     *
     * @default []
     */
    private List<String> authority;

    /**
     * 徽标
     */
    private String badge;

    /**
     * 徽标类型
     */
    private String badgeType; // "dot" or "normal"

    /**
     * 徽标颜色
     */
    private String badgeVariants; // options: "default", "destructive", "primary", "success", "warning", or string

    /**
     * 当前路由的子级在菜单中不展现
     *
     * @default false
     */
    private Boolean hideChildrenInMenu = false;

    /**
     * 当前路由在面包屑中不展现
     *
     * @default false
     */
    private Boolean hideInBreadcrumb = false;

    /**
     * 当前路由在菜单中不展现
     *
     * @default false
     */
    private Boolean hideInMenu = false;

    /**
     * 当前路由在标签页不展现
     *
     * @default false
     */
    private Boolean hideInTab = false;

    /**
     * 图标（菜单/tab）
     */
    private String icon;

    /**
     * iframe 地址
     */
    private String iframeSrc;

    /**
     * 忽略权限，直接可以访问
     *
     * @default false
     */
    private Boolean ignoreAccess = false;

    /**
     * 开启KeepAlive缓存
     */
    private Boolean keepAlive;

    /**
     * 外链-跳转路径
     */
    private String link;

    /**
     * 路由是否已经加载过
     */
    private Boolean loaded;

    /**
     * 标签页最大打开数量
     *
     * @default false
     */
    private Integer maxNumOfOpenTab;

    /**
     * 菜单可以看到，但是访问会被重定向到403
     */
    private Boolean menuVisibleWithForbidden;

    /**
     * 在新窗口打开
     */
    private Boolean openInNewWindow;

    /**
     * 用于路由->菜单排序
     */
    private Integer order;

    /**
     * 菜单所携带的参数
     */
    private Map<String, Object> query;

    /**
     * 标题名称
     */
    private String title;


}
