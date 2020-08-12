package soochow.zmq.service;

import org.apache.commons.lang3.tuple.Pair;
import soochow.zmq.model.ResultVO;
import soochow.zmq.model.User;

public interface UserManageService {
    ResultVO<User> register(User user);

    Pair<Boolean, String> resetPwd(String account, String baseRequestPath);

    Boolean frozen(User user);
}
