package com.yuhao.TeachingServiceSystem.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import com.yuhao.TeachingServiceSystem.common.SystemConfigType;
import com.yuhao.TeachingServiceSystem.dto.SystemConfigDTO;
import com.yuhao.TeachingServiceSystem.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SystemConfigCache {

    @Autowired
    private SystemConfigService systemConfigService;

    private Map<String, String> map = new ConcurrentHashMap<String, String>();

    @PostConstruct
    public void init() {
        refresh();
    }

    //@Scheduled(fixedDelay = 60000)
    public void refresh() {
        List<SystemConfigDTO> configs = systemConfigService.find(new SystemConfigDTO(), null);
        Map<String, String> tmpMap = new HashMap<String, String>();
        for (SystemConfigDTO dto : configs) {
            tmpMap.put(dto.getKey().toLowerCase(), dto.getValue() == null ? "" : dto.getValue());
        }
        map.putAll(tmpMap);
    }

    public String get(SystemConfigType key) {
        return map.get(key.value().toLowerCase());
    }
}
