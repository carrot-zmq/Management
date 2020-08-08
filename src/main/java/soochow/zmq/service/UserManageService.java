package soochow.zmq.service;

import soochow.zmq.model.ResultVO;
import soochow.zmq.model.User;

public interface UserManageService {
    ResultVO<User> register(User user);
}
