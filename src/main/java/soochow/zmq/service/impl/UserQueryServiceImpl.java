package soochow.zmq.service.impl;

import org.apache.commons.lang3.StringUtils;
import soochow.zmq.dao.mapper.UserMapper;
import soochow.zmq.model.User;
import soochow.zmq.service.UserQueryService;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

public class UserQueryServiceImpl implements UserQueryService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<User> queryAll(){
        return userMapper.queryAll();
    }

    @Override
    public User queryAccount(String account) {
        if (StringUtils.isBlank(account)) {
            return null;
        }
        return userMapper.queryAccount(account);

    }

    @Override
    public List<User> queryByLike(String nameortel)  {
        if (StringUtils.isBlank(nameortel)){
            return userMapper.queryAll();
        }
        else {
            return userMapper.queryByLike(nameortel);
        }
    }

    @Override
    public int countByTenant(long t_id){
        if(t_id==0)
        {
            return 0;
        }
        else
        {
            return userMapper.countByTenant(t_id);
        }
    }
}
