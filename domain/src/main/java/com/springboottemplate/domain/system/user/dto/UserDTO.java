package com.springboottemplate.domain.system.user.dto;

import cn.hutool.core.bean.BeanUtil;

import com.springboottemplate.domain.common.cache.CacheCenter;
import com.springboottemplate.domain.system.dept.db.SysDeptEntity;
import com.springboottemplate.domain.system.post.db.SysPostEntity;
import com.springboottemplate.domain.system.role.db.SysRoleEntity;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import com.springboottemplate.domain.system.user.db.SearchUserDO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Schema(name = "用户列表")
@Data
public class UserDTO {

    public UserDTO(SysUserEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

            SysDeptEntity dept = CacheCenter.deptCache.get(entity.getDeptId() + "");
            if (dept != null) {
                this.deptName = dept.getDeptName();
            }

            SysUserEntity creator = CacheCenter.userCache.getObjectById(entity.getCreatorId());
            if (creator != null) {
                this.creatorName = creator.getUsername();
            }

            if (entity.getRoleId() != null) {
                SysRoleEntity roleEntity = CacheCenter.roleCache.getObjectById(entity.getRoleId());
                this.roleName = roleEntity != null ? roleEntity.getRoleName() : "";
            }

            if (entity.getPostId() != null) {
                SysPostEntity post = CacheCenter.postCache.getObjectById(entity.getRoleId());
                this.postName = post != null ? post.getPostName() : "";
            }

        }
    }

    public UserDTO(SearchUserDO entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);

            if (entity.getRoleId() != null) {
                SysRoleEntity roleEntity = CacheCenter.roleCache.getObjectById(entity.getRoleId());
                this.roleName = roleEntity != null ? roleEntity.getRoleName() : "";
            }
        }
    }


    @Schema(name = "用户ID")
    private Long userId;

    @Schema(name = "职位ID")
    private Long postId;

    @Schema(name = "职位名称")
    private String postName;

    @Schema(name = "角色ID")
    private Long roleId;

    @Schema(name = "角色名称")
    private String roleName;

    @Schema(name = "部门ID")
    private Long deptId;

    @Schema(name = "部门名称")
    private String deptName;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "用户类型")
    private Integer userType;

    @Schema(name = "邮件")
    private String email;

    @Schema(name = "号码")
    private String phoneNumber;

    @Schema(name = "性别")
    private Integer sex;

    @Schema(name = "用户头像")
    private String avatar;

    @Schema(name = "状态")
    private Integer status;

    @Schema(name = "IP")
    private String loginIp;

    @Schema(name = "登录时间")
    private Date loginDate;

    @Schema(name = "创建者ID")
    private Long creatorId;

    @Schema(name = "创建者")
    private String creatorName;

    @Schema(name = "创建时间")
    private Date createTime;

    @Schema(name = "修改者ID")
    private Long updaterId;

    @Schema(name = "修改者")
    private String updaterName;

    @Schema(name = "修改时间")
    private Date updateTime;

    @Schema(name = "备注")
    private String remark;

}
