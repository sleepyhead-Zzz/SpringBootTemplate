package com.springboottemplate.domain.common.command;

import cn.hutool.core.collection.CollUtil;
import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
public class BulkOperationCommand<T> {

    public BulkOperationCommand(List<T> idList) {
        if (CollUtil.isEmpty(idList)) {
            throw new ApiException(ErrorCode.Business.COMMON_BULK_DELETE_IDS_IS_INVALID);
        }
        // 移除重复元素
        this.ids = new HashSet<>(idList);
    }

    private Set<T> ids;

}
