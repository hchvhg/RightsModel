package com.bsoft.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsoft.bean.Resource;
import com.bsoft.mapper.ResourceMapper;
import com.bsoft.service.ResourceService;

/**
 * ResourceServiceImpl
 * @description TODO
 * @author zk
 * @date 2019年1月17日 下午2:21:46
 * @version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public List<Map<String,Object>> getResourceList(String resourceName) {
        return resourceMapper.getResourceList(resourceName);
    }
    @Override
    public void addResource(Resource resource) {
        resourceMapper.insert(resource);
    }
    @Override
    public void updateResource(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
    }
    @Override
    public void deleteResourceById(Long resourceId) {
        resourceMapper.deleteByPrimaryKey(resourceId);
    }

}
