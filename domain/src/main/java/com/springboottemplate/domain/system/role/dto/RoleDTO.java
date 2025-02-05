package com.springboottemplate.domain.system.role.dto;


import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 * @author Sleepyhead
 */
@Data
@Schema(description = "角色列表")
public class RoleDTO {

    public RoleDTO(SysRoleEntity entity) {
        if (entity != null) {
            this.roleId = entity.getRoleId();
            this.roleName = entity.getRoleName();
            this.roleKey = entity.getRoleKey();
            this.roleSort = entity.getRoleSort();
            this.createTime = entity.getCreateTime();
            this.status = entity.getStatus();
            this.remark = entity.getRemark();
            this.dataScope = entity.getDataScope();
        }
    }

    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "角色名称")
    private String roleName;
    @Schema(description = "角色标识")
    private String roleKey;
    @Schema(description = "角色排序")
    private Integer roleSort;
    @Schema(description = "角色状态")
    private Integer status;
    @Schema(description = "备注")
    private String remark;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "数据范围")
    private Integer dataScope;

    private List<Long> selectedMenuList;

    private List<Long> selectedDeptList;
}
