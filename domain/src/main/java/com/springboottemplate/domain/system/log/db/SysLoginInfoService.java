package com.springboottemplate.domain.system.log.db;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-06
 */
@Primary
public interface SysLoginInfoService extends IService<SysLoginInfoEntity> {

}
