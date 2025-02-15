package com.springboottemplate.common.core.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.springboottemplate.common.time.DatePickUtil;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 如果是简单的排序 和 时间范围筛选  可以使用内置的这几个字段
 *
 * @author Sleepyhead
 */
@Data
public abstract class AbstractQuery<T> {

    @Parameter(description = "排序字段", schema = @Schema(type = "string"))
    protected String orderColumn;

    @Parameter(description = "排序方向", schema = @Schema(type = "string"))
    protected String orderDirection;

    @Parameter(description = "时间范围字段名", schema = @Schema(type = "string"))
    protected String timeRangeColumn;

    @Parameter(description = "开始时间", schema = @Schema(type = "date"))
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginTime;

    @Parameter(description = "结束时间", schema = @Schema(type = "date"))
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

    private static final String ASC = "ascending";
    private static final String DESC = "descending";

    /**
     * 生成query conditions
     *
     * @return 添加条件后的QueryWrapper
     */
    public QueryWrapper<T> toQueryWrapper() {
        QueryWrapper<T> queryWrapper = addQueryCondition();
        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper);

        return queryWrapper;
    }

    public abstract QueryWrapper<T> addQueryCondition();

    public void addSortCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper == null || StrUtil.isEmpty(orderColumn)) {
            return;
        }

        Boolean sortDirection = convertSortDirection();
        if (sortDirection != null) {
            queryWrapper.orderBy(StrUtil.isNotEmpty(orderColumn), sortDirection,
                StrUtil.toUnderlineCase(orderColumn));
        }
    }

    public void addTimeCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper != null
            && StrUtil.isNotEmpty(this.timeRangeColumn)) {
            queryWrapper
                .ge(beginTime != null, StrUtil.toUnderlineCase(timeRangeColumn),
                    DatePickUtil.getBeginOfTheDay(beginTime))
                .le(endTime != null, StrUtil.toUnderlineCase(timeRangeColumn), DatePickUtil.getEndOfTheDay(endTime));
        }
    }

    /**
     * 获取前端传来的排序方向  转换成MyBatisPlus所需的排序参数 boolean=isAsc
     *
     * @return 排序顺序， null为无排序
     */
    public Boolean convertSortDirection() {
        Boolean isAsc = null;
        if (StrUtil.isEmpty(this.orderDirection)) {
            return isAsc;
        }

        if (ASC.equals(this.orderDirection)) {
            isAsc = true;
        }
        if (DESC.equals(this.orderDirection)) {
            isAsc = false;
        }

        return isAsc;
    }

}
