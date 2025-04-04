package com.springboottemplate.domain.system.post.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottemplate.common.core.page.AbstractPageQuery;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sleepyhead
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostQuery extends AbstractPageQuery<SysPostEntity> {

    private String postCode;
    private String postName;
    private Integer status;

    @Override
    public QueryWrapper<SysPostEntity> addQueryCondition() {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<SysPostEntity>()
            .eq(status != null, "status", status)
            .eq(StrUtil.isNotEmpty(postCode), "post_code", postCode)
            .like(StrUtil.isNotEmpty(postName), "post_name", postName);
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("post_sort");
        }
        this.setTimeRangeColumn("create_time");

        return queryWrapper;
    }
}
