package com.springboottemplate.domain.common.cache;

import com.springboottemplate.domain.system.dept.db.SysDeptEntity;
import com.springboottemplate.domain.system.dept.db.SysDeptService;
import com.springboottemplate.infrastructure.cache.guava.AbstractGuavaCacheTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Sleepyhead
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class GuavaCacheService {

//    private final SysConfigService configService;

    private final SysDeptService deptService;

//    public final AbstractGuavaCacheTemplate<String> configCache = new AbstractGuavaCacheTemplate<String>() {
//        @Override
//        public String getObjectFromDb(Object id) {
//            return configService.getConfigValueByKey(id.toString());
//        }
//    };

    public final AbstractGuavaCacheTemplate<SysDeptEntity> deptCache = new AbstractGuavaCacheTemplate<SysDeptEntity>() {
        @Override
        public SysDeptEntity getObjectFromDb(Object id) {
            return deptService.getById(id.toString());
        }
    };


}
