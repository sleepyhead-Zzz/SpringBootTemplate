package com.springboottemplate.admin.controller.issue;

import com.springboottemplate.common.core.base.BaseController;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.domain.issue.repair.RepairApplicationService;
import com.springboottemplate.domain.issue.repair.command.AddRepairCommand;
import com.springboottemplate.domain.issue.repair.command.UpdateRepairCommand;
import com.springboottemplate.domain.issue.repair.dto.RepairDTO;
import com.springboottemplate.domain.issue.repair.query.RepairQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Tag(name = "报修API", description = "报修相关接口")
@RestController
@RequiredArgsConstructor
public class RepairController extends BaseController {

    private final RepairApplicationService repairApplicationService;

    /**
     * 获取报修列表
     */
    @Operation(summary = "报修列表")
    @GetMapping("/repairs")
    public ResponseDTO<List<RepairDTO>> list(RepairQuery query) {
        List<RepairDTO> repairList = repairApplicationService.getRepairList(query);
        return ResponseDTO.ok(repairList);
    }

    /**
     * 根据报修编号获取详细信息
     */
    @Operation(summary = "报修详情")
    @GetMapping(value = "/repair/{repairId}")
    public ResponseDTO<RepairDTO> getInfo(@PathVariable Long repairId) {
        RepairDTO repair = repairApplicationService.getRepairInfo(repairId);
        return ResponseDTO.ok(repair);
    }

    /**
     * 新增报修
     */
    @Operation(summary = "新增报修")

    @PostMapping("/repair")
    public ResponseDTO<Void> add(@RequestBody AddRepairCommand addCommand) {
        repairApplicationService.addRepair(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改报修
     */
    @Operation(summary = "修改报修")
    @PutMapping("/repair/{repairId}")
    public ResponseDTO<Void> edit(@PathVariable("repairId") Long repairId,
        @RequestBody UpdateRepairCommand updateCommand) {
        updateCommand.setRepairId(repairId);
        repairApplicationService.updateRepair(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除报修
     */
    @Operation(summary = "删除报修")

    @DeleteMapping("/repair/{repairId}")
    public ResponseDTO<Void> remove(@PathVariable @NotNull Long repairId) {
        repairApplicationService.removeRepair(repairId);
        return ResponseDTO.ok();
    }
}
