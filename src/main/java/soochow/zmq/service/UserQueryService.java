package soochow.zmq.service;

import soochow.zmq.model.User;

public interface UserQueryService {

    User queryAccount(String account);
}
