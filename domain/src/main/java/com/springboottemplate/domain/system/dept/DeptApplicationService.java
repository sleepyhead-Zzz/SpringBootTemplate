package com.springboottemplate.domain.system.dept;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.springboottemplate.domain.system.dept.command.AddDeptCommand;
import com.springboottemplate.domain.system.dept.command.UpdateDeptCommand;
import com.springboottemplate.domain.system.dept.db.SysDeptEntity;
import com.springboottemplate.domain.system.dept.db.SysDeptService;
import com.springboottemplate.domain.system.dept.dto.DeptDTO;
import com.springboottemplate.domain.system.dept.model.DeptModel;
import com.springboottemplate.domain.system.dept.model.DeptModelFactory;
import com.springboottemplate.domain.system.dept.query.DeptQuery;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 部门服务
 *
 * @author Sleepyhead
 */
@Service
@RequiredArgsConstructor
public class DeptApplicationService {

    private final SysDeptService deptService;


    private final DeptModelFactory deptModelFactory;


    public List<DeptDTO> getDeptList(DeptQuery query) {
        List<SysDeptEntity> list = deptService.list(query.toQueryWrapper());
        return list.stream().map(DeptDTO::new).collect(Collectors.toList());
    }

    public DeptDTO getDeptInfo(Long id) {
        SysDeptEntity byId = deptService.getById(id);
        return new DeptDTO(byId);
    }

    public List<Tree<Long>> getDeptTree() {
        TreeNodeConfig config = new TreeNodeConfig();
        List<SysDeptEntity> list = deptService.list();
        config.setIdKey("deptId");
        return TreeUtil.build(list, 0L, config,(dept, tree) -> {
            tree.setId(dept.getDeptId());
            tree.setParentId(dept.getParentId());
            tree.putExtra("label", dept.getDeptName());
        });
    }


    public void addDept(AddDeptCommand addCommand) {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.loadAddCommand(addCommand);

        deptModel.checkDeptNameUnique();
        deptModel.generateAncestors();

        deptModel.insert();
    }

    public void updateDept(UpdateDeptCommand updateCommand) {
        DeptModel deptModel = deptModelFactory.loadById(updateCommand.getDeptId());
        deptModel.loadUpdateCommand(updateCommand);

        deptModel.checkDeptNameUnique();
        deptModel.checkParentIdConflict();
        deptModel.checkStatusAllowChange();
        deptModel.generateAncestors();

        deptModel.updateById();
    }

    public void removeDept(Long deptId) {
        DeptModel deptModel = deptModelFactory.loadById(deptId);

        deptModel.checkHasChildDept();
        deptModel.checkDeptAssignedToUsers();

        deptModel.deleteById();
    }


}
