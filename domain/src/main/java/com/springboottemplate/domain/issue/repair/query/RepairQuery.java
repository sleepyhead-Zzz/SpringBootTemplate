package com.springboottemplate.domain.issue.repair.query;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboottemplate.common.core.page.AbstractQuery;
import com.springboottemplate.domain.issue.repair.db.RepairEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RepairQuery extends AbstractQuery<RepairEntity> {

    @Override
    public QueryWrapper<RepairEntity> addQueryCondition() {
        return null;
    }
}
