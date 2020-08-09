package soochow.zmq.service;

import soochow.zmq.model.Tenant;

public interface TenantService {
    Tenant create(Tenant tenant);
}
