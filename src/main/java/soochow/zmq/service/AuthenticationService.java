package soochow.zmq.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {


    boolean isAuthenticated(HttpServletRequest request);

    /**
     * 验证用户名密码是否正确
     *
     * @param id
     * @param pwd
     * @return
     */
    String login(String id, String pwd, HttpServletRequest request, HttpServletResponse response) throws IOException;

}