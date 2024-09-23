package com.springboottemplate.domain.system.log.db;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Primary;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author valarchie
 * @since 2022-06-08
 */
@Primary
public interface SysOperationLogService extends IService<SysOperationLogEntity> {

}
