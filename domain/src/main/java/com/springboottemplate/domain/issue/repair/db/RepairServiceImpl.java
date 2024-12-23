package com.springboottemplate.domain.issue.repair.db;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author sleepyhead
 * @description 针对表【repair】的数据库操作Service实现
 * @createDate 2024-09-18 10:28:16
 */
@Service
@RequiredArgsConstructor
public class RepairServiceImpl extends ServiceImpl<RepairMapper, RepairEntity>
    implements RepairService {

}




