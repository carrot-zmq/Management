package soochow.zmq.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import soochow.zmq.model.Tenant;

@Mapper
public interface TenantMapper {

    Tenant add(Tenant tenant);

    boolean update(Tenant tenant);

    boolean updateTenantDesc(Long tenantId, String desc);

    boolean remove(Tenant tenant);

    Tenant queryById(@Param("tenantId") long tenantId);

}
