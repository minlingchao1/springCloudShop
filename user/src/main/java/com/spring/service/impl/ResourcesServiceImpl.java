package com.spring.service.impl;

import com.google.common.base.Preconditions;
import com.spring.domain.model.Resource;
import com.spring.domain.model.Role;
import com.spring.domain.model.VO.RoleResourcesVO;
import com.spring.persistence.ResourcesMapper;
import com.spring.persistence.RoleResourcesMapper;
import com.spring.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 资源
 * @Author ErnestCheng
 * @Date 2017/6/26.
 */
@Service
public class ResourcesServiceImpl implements ResourcesService{

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private RoleResourcesMapper roleResourcesMapper;
    @Override
    public int addResource(Resource resource) {
        Preconditions.checkNotNull(resource);
        resource.setCreateTime(OffsetDateTime.now());
        return resourcesMapper.addResource(resource);
    }

    @Override
    public List<Resource> listResources(Integer status) {
        Preconditions.checkNotNull(status);
        List<Resource> resourceList=resourcesMapper.listResources(status);
        if(resourceList!=null&&resourceList.size()>0){
            return resourceList;
        }else{
            return new ArrayList<>();
        }
    }
    @Override
    public List<String> listRoleByResourceId(Integer id) {
       List<RoleResourcesVO> roleResourcesVOS= roleResourcesMapper.listRoleByResourcesId(id);
       if(roleResourcesVOS!=null&&roleResourcesVOS.size()>0){
             List<String> roleNameList=roleResourcesVOS.stream().map(roleResourcesVO -> roleResourcesVO.getRoleName()).collect(Collectors.toList());
             return roleNameList;
       }else{
            return new ArrayList<>();
       }
    }
}
