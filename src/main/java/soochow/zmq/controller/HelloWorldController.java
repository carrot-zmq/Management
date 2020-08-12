//package soochow.zmq.controller;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import soochow.zmq.dao.mapper.TenantMapper;
//import soochow.zmq.dao.mapper.UserMapper;
//import soochow.zmq.model.ResultVO;
//import soochow.zmq.model.User;
//import soochow.zmq.service.UserManageService;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//
//@Controller
//@RestController
//public class HelloWorldController {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
//
//
//    @Resource
//    UserManageService userManageService;
//
//    @PutMapping("/registerAccount")
//    public ResultVO<User> registerAccount(@RequestBody User user) {
//        if (null == user) {
//            return ResultVO.fail("传入参数为空");
//        }
//
//        try {
//            return userManageService.register(user);
//        } catch (Throwable throwable) {
//            LOGGER.error("注册用户异常", throwable);
//            return ResultVO.fail("系统异常:" + throwable.getMessage());
//        }
//    }
//
//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello, MQZhang";
//    }
//
//    @GetMapping("/queryUser/{id}")
//    public User query(@PathVariable long id) {
//        User user = userMapper.queryById(id);
//        return null == user ? new User() : user;
//
//    }
//
//    @PutMapping("/addUser")
//    @Deprecated
//    public Object addUser(@RequestBody User user) {
//        if (null == user) {
//            return "添加对象不能为空";
//        }
//        String validateMsg = user.validate();
//        if (null != validateMsg) {
//            return validateMsg;
//        }
//
//        long id = userMapper.add(user);
//        user.setU_id(id);
//        return user;
//    }
//
//
//
//}
