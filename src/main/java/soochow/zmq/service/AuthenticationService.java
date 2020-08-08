package soochow.zmq.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {


    boolean isAuthenticated(HttpServletRequest request);

    /**
     * 验证用户名密码是否正确
     *
     * @param id
     * @param pwd
     * @return
     */
    boolean login(String id, String pwd, HttpServletRequest request, HttpServletResponse response);

}