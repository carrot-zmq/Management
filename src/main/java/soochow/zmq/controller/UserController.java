package soochow.zmq.controller;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import soochow.zmq.dao.mapper.UserMapper;
import soochow.zmq.model.ResultVO;
import soochow.zmq.model.User;
import soochow.zmq.service.AuthenticationService;
import soochow.zmq.service.UserManageService;
import soochow.zmq.service.UserQueryService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserManageService userManageService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private AuthenticationService authenticationService;


    @PutMapping("/registerAccount")
    public ResultVO<User> registerAccount(@RequestBody User user) {
        if (null == user) {
            return ResultVO.fail("传入参数为空");
        }

        try {
            return userManageService.register(user);
        } catch (Throwable throwable) {
            LOGGER.error("注册用户异常", throwable);
            return ResultVO.fail("系统异常:" + throwable.getMessage());
        }
    }

    @PutMapping("/addUser")
    @Deprecated
    public Object addUser(@RequestBody User user) {
        if (null == user) {
            return "添加对象不能为空";
        }
        String validateMsg = user.validate();
        if (null != validateMsg) {
            return validateMsg;
        }

        long id = userMapper.add(user);
        user.setU_id(id);
        return user;
    }

    @PutMapping("/frozen")
    public boolean frozen(@RequestBody User user){
        return userManageService.frozen(user);
    }

    @PostMapping("/resetPassword")
    public void resetPwd(@RequestParam("account") String account, HttpServletRequest request) {
        //todo 返回值重新设计一下，考虑交互
        if (StringUtils.isBlank(account)) {
            return;
        }
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath() + "/";
        log.info("another way:{}", request.getRequestURL());


        Pair<Boolean, String> result = userManageService.resetPwd(account, basePath);

    }

    @PostMapping("/login")
    public Object login(@RequestParam("username") String account, @RequestParam("password") String password, Model model,
                      HttpServletRequest request, HttpServletResponse response) throws IOException {

        String loginResult = authenticationService.login(account, password, request,response);

        if (loginResult.indexOf("登陆成功")!=-1) {
            return "index";
        } else if (loginResult.equals("账户冻结"))
        {
            model.addAttribute("errorMsg", "账户冻结");
            return "login";
        }
        else
            {
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/queryUser/{id}")
    public User query(@PathVariable long id) {
        User user = userMapper.queryById(id);
        return null == user ? new User() : user;

    }







}
