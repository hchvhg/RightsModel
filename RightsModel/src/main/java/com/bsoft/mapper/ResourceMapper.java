package com.bsoft.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bsoft.bean.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Map<String,Object>> getResourceList(@Param("resourceName") String resourceName);
}