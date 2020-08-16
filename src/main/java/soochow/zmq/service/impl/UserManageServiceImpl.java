package soochow.zmq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import soochow.zmq.dao.mapper.TenantMapper;
import soochow.zmq.dao.mapper.UserMapper;
import soochow.zmq.model.AccountStatus;
import soochow.zmq.model.ResultVO;
import soochow.zmq.model.Tenant;
import soochow.zmq.model.User;
import soochow.zmq.service.EmailService;
import soochow.zmq.service.PwdValidator;
import soochow.zmq.service.UserManageService;
import soochow.zmq.service.UserQueryService;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.*;


@Slf4j
public class UserManageServiceImpl implements UserManageService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Resource
    private PwdValidator pwdValidator;

    @Resource
    private EmailService emailService;

    @Resource
    UserQueryService userQueryService;

    final SecureRandom secureRandom = new SecureRandom();

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
        user.normalize();
        // todo 可以通过数据库添加唯一索引之类的约束 放置重复用户名，重复有效，重复手机号等，也可以业务逻辑先去查数据库，看是否能查到，如果能，
        // 则说明已经被注册，返回失败
        try {
            userMapper.add(user);
            log.info("注册成功:{}", user.getU_id());
            return ResultVO.success(user);
        } catch (Exception ex) {
            log.error("注册发生异常", ex);
            return ResultVO.fail(ex.getMessage());
        } catch (Throwable throwable) {
            log.error("注册发生严重错误", throwable);
            return ResultVO.fail(throwable.getMessage());
        }
    }

    @Override
    public Pair<Boolean, String> resetPwd(String account, String baseRequestPath) {
        User user = userQueryService.queryAccount(account);
        if (null == user) {
            return ImmutablePair.of(false, "用户不存在");
        }

        String secretKey = RandomStringUtils.random(20, 0, 0, true, true, null, secureRandom); // 密钥
        Date expiredDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期

        long date = expiredDate.getTime() / 1000 * 1000;

        String key = user.getU_name() + "$" + date + "$" + secretKey;

        String digitalSignature = DigestUtils.md5DigestAsHex(key.getBytes());

        Map<String, Object> params = new HashMap<>();
        params.put("expiredDate", expiredDate);
        params.put("u_id", user.getU_id());
        params.put("secretKey", digitalSignature);

        int updateRes = userMapper.updateUserPwdReset(params);
        if (0 == updateRes) {
            return ImmutablePair.of(false, "oops, something unusual happened! Try it again");
        }

        boolean sendResult = sendmail(user, baseRequestPath, digitalSignature);

        if (sendResult) {
            return ImmutablePair.of(true, "重置密码邮件已经发送，请登陆邮箱进行重置！");
        } else {
            return ImmutablePair.of(false, "重置邮件发送失败!");
        }
    }

    @Override
    public Boolean frozen(User user){
        if (user.getU_status()!= AccountStatus.Frozen){
            user.setU_status(AccountStatus.Frozen);
            Map<String, Object> params = new HashMap<>();
            params.put("attribute", "u_status");
            params.put("value", AccountStatus.Frozen);
            params.put("u_id", user.getU_id());
            if (0!=userMapper.alterAttribute(params)){return true;};
        }

        return false;
    }
    private boolean sendmail(User user, String baseRequestPath, String digitalSignature) {

        String resetPassHref = baseRequestPath + "checkLink="
                + digitalSignature + "&userName=" + user.getU_name();

        String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href="
                + resetPassHref + " target='_BLANK'>" + resetPassHref
                + "</a>  或者    <a href=" + resetPassHref
                + " target='_BLANK'>点击我重新设置密码</a>"
                + "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'";

        return emailService.send("XXX网站密码重置", user.getU_email(), emailContent);
    }

}
