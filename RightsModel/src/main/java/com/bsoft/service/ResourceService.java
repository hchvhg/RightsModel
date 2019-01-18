package com.bsoft.service;

import java.util.List;
import java.util.Map;

import com.bsoft.bean.Resource;

/**
 * ResourceService
 * @description TODO
 * @author zk
 * @date 2019年1月17日 下午2:20:19
 * @version 1.0
 */
public interface ResourceService {
    List<Map<String,Object>> getResourceList(String resourceName);

    void addResource(Resource resource);

    void updateResource(Resource resource);

    void deleteResourceById(Long resourceId);
}
