package com.springboottemplate.domain.issue.repair;

import com.springboottemplate.domain.issue.repair.command.AddRepairCommand;
import com.springboottemplate.domain.issue.repair.command.UpdateRepairCommand;
import com.springboottemplate.domain.issue.repair.db.RepairEntity;
import com.springboottemplate.domain.issue.repair.db.RepairService;
import com.springboottemplate.domain.issue.repair.dto.RepairDTO;
import com.springboottemplate.domain.issue.repair.model.RepairModel;
import com.springboottemplate.domain.issue.repair.model.RepairModelFactory;
import com.springboottemplate.domain.issue.repair.query.RepairQuery;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepairApplicationService {

    private final RepairService repairService;
    private final RepairModelFactory repairModelFactory;

    public List<RepairDTO> getRepairList(RepairQuery query) {
        List<RepairEntity> list = repairService.list(query.toQueryWrapper());
        return list.stream().map(RepairDTO::new).collect(Collectors.toList());
    }

    public RepairDTO getRepairInfo(Long id) {
        RepairEntity byId = repairService.getById(id);
        return new RepairDTO(byId);
    }

    public void addRepair(AddRepairCommand addCommand) {
        RepairModel repairModel = repairModelFactory.create();
        repairModel.loadAddCommand(addCommand);

        repairModel.insert();
    }

    public void updateRepair(UpdateRepairCommand updateCommand) {
        RepairModel repairModel = repairModelFactory.loadById(updateCommand.getRepairId());
        repairModel.loadUpdateCommand(updateCommand);

        repairModel.updateById();
    }

    public void removeRepair(Long repairId) {
        RepairModel repairModel = repairModelFactory.loadById(repairId);

        repairModel.deleteById();
    }

}
