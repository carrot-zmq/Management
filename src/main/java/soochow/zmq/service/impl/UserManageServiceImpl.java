package soochow.zmq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import soochow.zmq.dao.mapper.TenantMapper;
import soochow.zmq.dao.mapper.UserMapper;
import soochow.zmq.model.ResultVO;
import soochow.zmq.model.Tenant;
import soochow.zmq.model.User;
import soochow.zmq.service.PwdValidator;
import soochow.zmq.service.UserManageService;

import javax.annotation.Resource;
import java.sql.SQLException;

public class UserManageServiceImpl implements UserManageService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Resource
    private PwdValidator pwdValidator;

    @Override
    public ResultVO<User> register(User user) {
        String validateResult = user.validate();
        if (null != validateResult) {
            return ResultVO.fail(validateResult);
        }
        if (!pwdValidator.isStrongEnough(user.getPwd())) {
            return ResultVO.fail("密码强度不够，至少6位, 不能纯数字，大/小写");
        }


        Tenant tenant = tenantMapper.queryById(user.getOwned_tenant());
        if (null == tenant) {
            return ResultVO.fail("用户所属租户无效");
        }
        // todo 可以通过数据库添加唯一索引之类的约束 放置重复用户名，重复有效，重复手机号等，也可以业务逻辑先去查数据库，看是否能查到，如果能，
        // 则说明已经被注册，返回失败
        try {
            long id = userMapper.add(user);
            user.setU_id(id);
            return ResultVO.success(user);
        } catch (Exception ex) {

        } catch (Throwable throwable) {

        }
    }
}
