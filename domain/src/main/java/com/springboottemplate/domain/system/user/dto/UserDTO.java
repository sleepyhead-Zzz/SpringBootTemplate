package com.springboottemplate.domain.system.user.dto;

import cn.hutool.core.bean.BeanUtil;
import com.springboottemplate.domain.system.user.db.SearchUserDO;
import com.springboottemplate.domain.system.user.db.SysUserEntity;
import java.util.Date;
import lombok.Data;

/**
 * @author valarchie
 */
@Data
public class UserDTO {

    public UserDTO(SysUserEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);


        }
    }

    public UserDTO(SearchUserDO entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }


    private Long userId;

    private Long postId;

    private String postName;

    private Long roleId;

    private String roleName;

    private Long deptId;

    private String deptName;

    private String username;

    private String nickname;

    private Integer userType;

    private String email;

    private String phoneNumber;

    private Integer sex;

    private String avatar;

    private Integer status;

    private String loginIp;

    private Date loginDate;

    private Long creatorId;

    private String creatorName;


    private Date createTime;


    private Long updaterId;


    private String updaterName;


    private Date updateTime;


    private String remark;

}
