package soochow.zmq.service.impl;

import org.apache.commons.lang3.StringUtils;
import soochow.zmq.model.AccountStatus;
import soochow.zmq.model.Role;
import soochow.zmq.model.User;
import soochow.zmq.service.AuthenticationService;
import soochow.zmq.service.RoleQueryService;
import soochow.zmq.service.UserQueryService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationServiceImpl implements AuthenticationService {
    private final static String AUTHENTICATION_KEY = "authenticationKey";

    @Resource
    private UserQueryService userQueryService;
    @Resource
    private RoleQueryService roleQueryService;

    @Override
    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return null != session && null != session.getAttribute(AUTHENTICATION_KEY);

    }

    @Override
    public String login(String id, String pwd, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (StringUtils.isBlank(id) || StringUtils.isBlank(pwd)) {
            return "登陆失败";
        }
        User user = userQueryService.queryAccount(id);
        if (null == user) {
            return "登陆失败";
        }
        if (!user.getPwd().equals(pwd)) {
            return "登陆失败";
        }

        if (user.getU_status() != AccountStatus.Active) {
            return "账户冻结";
        }

        HttpSession session = request.getSession();
        session.setAttribute(AUTHENTICATION_KEY, session.getId());
        session.setAttribute("user",user);

        response.addCookie(new Cookie(AUTHENTICATION_KEY, session.getId()));

        if (session.isNew()){
            response.getWriter().print("session已经创建成功，session的id是："+session.getId());
        }
        else
        {
            response.getWriter().print("服务器已存在该session，id为："+session.getId());
        }

        if (isAuthenticated(request))
        {Role role=roleQueryService.queryById(user.getU_id());
        String rName=role.getR_name();
        rName= user.getU_name()+":"+rName;

        return rName+" "+"登陆成功";}
        else
            return "登录失败";
    }
}
