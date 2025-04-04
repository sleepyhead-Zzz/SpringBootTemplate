package com.springboottemplate.admin.controller.system;

import cn.hutool.core.lang.tree.Tree;
import com.springboottemplate.common.core.base.BaseController;
import com.springboottemplate.common.core.dto.ResponseDTO;
import com.springboottemplate.domain.system.dept.DeptApplicationService;
import com.springboottemplate.domain.system.dept.command.AddDeptCommand;
import com.springboottemplate.domain.system.dept.command.UpdateDeptCommand;
import com.springboottemplate.domain.system.dept.dto.DeptDTO;
import com.springboottemplate.domain.system.dept.query.DeptQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门信息
 *
 * @author Sleepyhead
 */
@Tag(name = "部门API", description = "部门相关的增删查改")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/dept")
@Validated
public class SysDeptController extends BaseController {

    private final DeptApplicationService deptApplicationService;

    /**
     * 获取部门列表
     */
    @Operation(summary = "部门列表")
    @GetMapping("/list")
    public ResponseDTO<List<DeptDTO>> listDept(@ParameterObject @ModelAttribute DeptQuery query) {
        List<DeptDTO> deptList = deptApplicationService.getDeptList(query);
        return ResponseDTO.ok(deptList);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @Operation(summary = "部门详情")

    @GetMapping(value = "/{deptId}")
    public ResponseDTO<DeptDTO> getDeptInfo(@PathVariable Long deptId) {
        DeptDTO dept = deptApplicationService.getDeptInfo(deptId);
        return ResponseDTO.ok(dept);
    }

    /**
     * 获取部门下拉树列表
     */
    @Operation(summary = "获取部门树级结构")
    @GetMapping("/dropdown")
    public ResponseDTO<List<Tree<Long>>> dropdownDeptList() {
        List<Tree<Long>> deptTree = deptApplicationService.getDeptTree();
        return ResponseDTO.ok(deptTree);
    }

    /**
     * 新增部门
     */
    @Operation(summary = "新增部门")
    @PostMapping("/")
    public ResponseDTO<Void> addDept(@Validated @RequestBody AddDeptCommand addCommand) {
        deptApplicationService.addDept(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改部门
     */
    @Operation(summary = "修改部门")
    @PreAuthorize("@permission.has('system:dept:edit') AND @dataScope.checkDeptId(#updateCommand.deptId)")
    @PutMapping("/{deptId}")
    public ResponseDTO<Void> editDept(@PathVariable("deptId") Long deptId,
        @Validated @RequestBody UpdateDeptCommand updateCommand) {
        updateCommand.setDeptId(deptId);
        deptApplicationService.updateDept(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除部门
     */
    @Operation(summary = "删除部门")
    @PreAuthorize("@permission.has('system:dept:remove') AND @dataScope.checkDeptId(#deptId)")
    @DeleteMapping("/{deptId}")
    public ResponseDTO<Void> removeDept(@PathVariable @NotNull Long deptId) {
        deptApplicationService.removeDept(deptId);
        return ResponseDTO.ok();
    }
}
