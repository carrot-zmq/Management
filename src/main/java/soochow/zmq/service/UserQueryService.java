package soochow.zmq.service;

import soochow.zmq.model.User;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

public interface UserQueryService {

    List<User> queryAll();

    User queryAccount(String account);

    List<User> queryByLike(String nameortel) ;

    int countByTenant(long t_id);
}
