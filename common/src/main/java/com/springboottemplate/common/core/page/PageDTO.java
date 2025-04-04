package com.springboottemplate.common.core.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

/**
 * 分页模型类
 *
 * @author Sleepyhead
 */
@Data
public class PageDTO<T> {

    /**
     * 总记录数
     */
    @Schema(description = "总记录数")

    private Long total;

    /**
     * 列表数据
     */
    @Schema(description = "列表数据")
    private List<T> rows;

    public PageDTO(List<T> list) {
        this.rows = list;
        this.total = (long) list.size();
    }

    public PageDTO(Page<T> page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

    public PageDTO(List<T> list, Long count) {
        this.rows = list;
        this.total = count;
    }

}
