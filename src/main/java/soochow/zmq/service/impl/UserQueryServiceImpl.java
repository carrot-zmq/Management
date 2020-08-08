package soochow.zmq.service.impl;

import org.apache.commons.lang3.StringUtils;
import soochow.zmq.dao.mapper.UserMapper;
import soochow.zmq.model.User;
import soochow.zmq.service.UserQueryService;

import javax.annotation.Resource;

public class UserQueryServiceImpl implements UserQueryService {

    @Resource
    UserMapper userMapper;

    @Override
    public User queryAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return userMapper.queryAccount(account);

    }
}
