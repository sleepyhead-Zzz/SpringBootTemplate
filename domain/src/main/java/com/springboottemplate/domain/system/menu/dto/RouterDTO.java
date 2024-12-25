package com.springboottemplate.domain.system.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.springboottemplate.common.utils.jackson.JacksonUtil;
import com.springboottemplate.domain.system.menu.db.SysMenuEntity;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 动态路由信息 必须加上@JsonInclude(Include.NON_NULL)的注解  否则传null值给Vue动态路由渲染时会出错
 *
 * @author valarchie
 */
@JsonInclude(Include.NON_NULL)
@Data
@NoArgsConstructor
public class RouterDTO {

    public RouterDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.name = entity.getName();
            this.path = entity.getPath();
            this.component = entity.getComponent();
            this.redirect = entity.getRedirect();
            if (JacksonUtil.isJson(entity.getMeta())) {
                this.meta = JacksonUtil.from(entity.getMeta(), RouterMeta.class);
            } else {
                this.meta = new RouterMeta();
            }
        }
    }

    /**
     * 路由名字  根据前端的要求   必须唯一 并按照前端项目的推荐  这个Name最好和组件的Name一样  使用菜单表中的router_name
     */
    private String name;

    /**
     * 路由路径地址
     */
    private String path;

    /**
     * 路由重定向
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 其他元素
     */
    private RouterMeta meta;

    /**
     * 子路由
     */
    private List<RouterDTO> children;

}
