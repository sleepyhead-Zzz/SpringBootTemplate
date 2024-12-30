package com.springboottemplate.domain.system.menu.dto;

import cn.hutool.core.util.StrUtil;
import com.springboottemplate.common.utils.jackson.JacksonUtil;
import com.springboottemplate.domain.system.menu.db.SysMenuEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author valarchie
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDetailDTO extends MenuDTO {

    public MenuDetailDTO(SysMenuEntity entity) {
        super(entity);
        if (entity == null) {
            return;
        }
        if (StrUtil.isNotEmpty(entity.getMeta()) && JacksonUtil.isJson(entity.getMeta())) {
            this.meta = JacksonUtil.from(entity.getMeta(), RouterMeta.class);
        }
        this.permission = entity.getPermission();
        this.order = meta.getOrder();
        this.keepAlive = meta.getKeepAlive();
        this.title = meta.getTitle();
    }

    private Integer order;
    private Boolean keepAlive;
    private String permission;
    private RouterMeta meta;
    private String title;

}
