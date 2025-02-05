package com.springboottemplate.domain.system.menu.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottemplate.common.core.page.AbstractQuery;
import com.springboottemplate.domain.system.menu.db.SysMenuEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuQuery extends AbstractQuery<SysMenuEntity> {

    // 直接交给前端筛选
//    private String menuName;
//    private Boolean isVisible;
//    private Integer status;
    private Boolean isButton;

    @Override
    public QueryWrapper<SysMenuEntity> addQueryCondition() {
        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(isButton != null, "is_button", isButton);
////            .like(StrUtil.isNotEmpty(menuName), "menu_name", menuName)
////            .eq(isVisible != null, "is_visible", isVisible)
////            .eq(status != null, "status", status);
        this.orderColumn = "parent_id";
        this.orderDirection = "descending";
        return queryWrapper;
    }
}
