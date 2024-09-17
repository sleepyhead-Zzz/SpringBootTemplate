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
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserEntity getUserByUserName(String userName);

    /**
     * 检测用户名是否
     *
     * @param userName 用户名
     * @return 校验结果
     */
    boolean isUserNameDuplicated(String userName);

    /**
     * 检测号码是否唯一
     *
     * @param phone 电话号码
     * @param userId 用户id
     * @return 校验结果
     */
    boolean isPhoneDuplicated(String phone, Long userId);

    /**
     * 检测邮箱是否唯一
     *
     * @param email 邮箱
     * @param userId 用户id
     * @return 校验结果
     */
    boolean isEmailDuplicated(String email, Long userId);

}
