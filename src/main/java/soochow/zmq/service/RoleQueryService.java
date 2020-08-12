package soochow.zmq.service;

import soochow.zmq.model.Role;

public interface RoleQueryService {

    Role queryById(long id);
}
