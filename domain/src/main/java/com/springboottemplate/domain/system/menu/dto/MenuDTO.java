package com.springboottemplate.domain.system.menu.dto;

import cn.hutool.core.util.StrUtil;
import com.springboottemplate.common.utils.jackson.JacksonUtil;
import com.springboottemplate.domain.system.menu.db.SysMenuEntity;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class MenuDTO {

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.id = entity.getMenu_id();
            this.parentId = entity.getParentId();
            this.name = entity.getName();
            this.menuType = entity.getMenuType();
            this.component = entity.getComponent();
            this.path = entity.getPath();
            this.redirect = entity.getRedirect();
            this.createTime = entity.getCreateTime();

            if (StrUtil.isNotEmpty(entity.getMeta()) && JacksonUtil.isJson(entity.getMeta())) {
                RouterMeta meta = JacksonUtil.from(entity.getMeta(), RouterMeta.class);
                this.order = meta.getOrder();
                this.icon = meta.getIcon();
            }
        }
    }

    private Long id;

    private Long parentId;

    private String name;

    private Integer menuType;

    private String component;

    private String path;

    private String redirect;

    private Integer order;

    private String icon;

    private String remark;

    private Date createTime;

    private Integer status;
}
