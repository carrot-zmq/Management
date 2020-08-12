package soochow.zmq.service.impl;

import soochow.zmq.dao.mapper.RoleMapper;
import soochow.zmq.model.Role;
import soochow.zmq.service.RoleQueryService;

import javax.annotation.Resource;

public class RoleQueryServiceImpl implements RoleQueryService {
    @Resource
    RoleMapper roleMapper;

    @Override
    public Role queryById(long id){

        return roleMapper.queryById(id);

    }



}
