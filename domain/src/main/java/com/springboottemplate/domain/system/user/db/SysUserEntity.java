package com.springboottemplate.domain.system.user.db;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class SysUserEntity {
    
    private Long id;

    private String username;

    private String phone;

}