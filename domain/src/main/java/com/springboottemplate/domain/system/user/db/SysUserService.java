package com.springboottemplate.domain.system.user.db;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Primary;

/**
 * @author sleepyhead
 * @description 针对表【sys_user】的数据库操作Service
 * @createDate 2024-09-02 12:26:06
 */
@Primary
public interface SysUserService extends IService<SysUserEntity> {
    /**
     * 通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserEntity getUserByUserName(String userName);

}
