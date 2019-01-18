package com.bsoft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bsoft.bean.Resource;
import com.bsoft.service.ResourceService;

/**
 * ResourceController
 * @description TODO
 * @author zk
 * @date 2019年1月17日 下午2:19:09
 * @version TODO
 */
@RestController
public class ResourceController {
    @Autowired
    private ResourceService resourceServcie;
    @GetMapping("getResourceList")
    public Map<String,Object> getResourceList(@RequestParam Map<String,String> paramMap){
        String resourceName = paramMap.get("resourceName");
        List<Map<String,Object>> resourceList = resourceServcie.getResourceList(resourceName);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("data", resourceList);
        resultMap.put("msg", "请求成功！");
        return resultMap;
    }
    
    @PostMapping("addResource")
    public Map<String,Object> addResource(@RequestParam Map<String,String> paramMap){
        String resourceName = paramMap.get("resourceName");
        String resourceType = paramMap.get("resourceType");
        String resourcePath = paramMap.get("resourcePath");
        String description = paramMap.get("description");
        Resource resource = new Resource();
        resource.setResourceName(resourceName);
        Short type = Short.valueOf(resourceType);
        resource.setResourceType(type);
        resource.setResourcePath(resourcePath);
        short status = 1;
        resource.setStatus(status);
        resource.setDescription(description);
        resourceServcie.addResource(resource);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "操作成功！");
        return resultMap;
    }
    @PostMapping("updateResource")
    public Map<String,Object> updateResource(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("resourceId");
        Long resourceId = null;
        if(StringUtils.isNotBlank(id)){
            resourceId = Long.valueOf(id);
        }
        String resourceName = paramMap.get("resourceName");
        String resourceType = paramMap.get("resourceType");
        String resourcePath = paramMap.get("resourcePath");
        String description = paramMap.get("description");
        Resource resource = new Resource();
        resource.setResourceId(resourceId);
        resource.setResourceName(resourceName);
        Short type = Short.valueOf(resourceType);
        resource.setResourceType(type);
        resource.setResourcePath(resourcePath);
        resource.setDescription(description);
        resourceServcie.updateResource(resource);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "操作成功！");
        return resultMap;
    }
    @PostMapping("/deleteResource")
    public Map<String,Object> deleteResource(@RequestParam Map<String,String> paramMap){
        String id = paramMap.get("resourceId");
        Long resourceId = null;
        if(StringUtils.isNotBlank(id)){
            resourceId = Long.valueOf(id);
        }
        resourceServcie.deleteResourceById(resourceId);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("code", 200);
        resultMap.put("msg", "操作成功！");
        return resultMap;
        
    }
}
