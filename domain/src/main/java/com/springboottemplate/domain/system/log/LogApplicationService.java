package com.springboottemplate.domain.system.log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboottemplate.common.core.page.PageDTO;
import com.springboottemplate.domain.common.command.BulkOperationCommand;
import com.springboottemplate.domain.system.log.db.SysLoginInfoEntity;
import com.springboottemplate.domain.system.log.db.SysLoginInfoService;
import com.springboottemplate.domain.system.log.db.SysOperationLogEntity;
import com.springboottemplate.domain.system.log.db.SysOperationLogService;
import com.springboottemplate.domain.system.log.dto.LoginLogDTO;
import com.springboottemplate.domain.system.log.dto.OperationLogDTO;
import com.springboottemplate.domain.system.log.query.LoginLogQuery;
import com.springboottemplate.domain.system.log.query.OperationLogQuery;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author valarchie
 */
@Service
@RequiredArgsConstructor
public class LogApplicationService {

    // TODO 命名到时候统一改成叫LoginLog
    private final SysLoginInfoService loginInfoService;

    private final SysOperationLogService operationLogService;

    public PageDTO<LoginLogDTO> getLoginInfoList(LoginLogQuery query) {
        Page<SysLoginInfoEntity> page = loginInfoService.page(query.toPage(), query.toQueryWrapper());
        List<LoginLogDTO> records = page.getRecords().stream().map(LoginLogDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void deleteLoginInfo(BulkOperationCommand<Long> deleteCommand) {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("info_id", deleteCommand.getIds());
        loginInfoService.remove(queryWrapper);
    }

    public PageDTO<OperationLogDTO> getOperationLogList(OperationLogQuery query) {
        Page<SysOperationLogEntity> page = operationLogService.page(query.toPage(), query.toQueryWrapper());
        List<OperationLogDTO> records = page.getRecords().stream().map(OperationLogDTO::new)
            .collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void deleteOperationLog(BulkOperationCommand<Long> deleteCommand) {
        operationLogService.removeBatchByIds(deleteCommand.getIds());
    }

}
