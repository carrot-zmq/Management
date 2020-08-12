package soochow.zmq.dao.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import soochow.zmq.model.Role;

import java.util.List;

@Mapper
public interface RoleMapper {

    /**
    添加一个角色
     */
    Role add(Role role);

    List<Role> queryAll();

    Role queryById(@Param("id") long id);



}
