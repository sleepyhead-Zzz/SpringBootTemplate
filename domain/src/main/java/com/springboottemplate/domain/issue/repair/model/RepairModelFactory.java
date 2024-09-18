package com.springboottemplate.domain.issue.repair.model;


import com.springboottemplate.common.exception.ApiException;
import com.springboottemplate.common.exception.error.ErrorCode;
import com.springboottemplate.domain.issue.repair.db.RepairEntity;
import com.springboottemplate.domain.issue.repair.db.RepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepairModelFactory {

    private final RepairService repairService;

    public RepairModel loadById(Long repairId) {
        RepairEntity byId = repairService.getById(repairId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, repairId, "报修");
        }
        return new RepairModel(byId, repairService);
    }

    public RepairModel create() {
        return new RepairModel(repairService);
    }

}
