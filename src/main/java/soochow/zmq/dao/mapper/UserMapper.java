package soochow.zmq.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.PostMapping;
import soochow.zmq.model.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 新加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 移除用户，可能是逻辑删，可能是物理删，看需要
     * @param user
     * @return
     */
    boolean remove(User user);


    /**
     * 通过账户名移除用户
     * 逻辑删
     * @param name
     * @return
     */
    boolean removeByUserName(@Param("name") String name);


    int updatePwd(Map<String, Object> params);

    int updateUserPwdReset(Map<String, Object> params);

    /**
     * 通过 id 查询
     * @param id
     * @return
     */
    User queryById(@Param("id") long id);

    /**
     * 通过账户名称查询
     * @param name
     * @return
     */
    User queryByName(@Param("name") String name);

    User queryAccount(@Param("account") String account);

    List<User> queryAll();

    List<User> queryByLike(@Param("nameortel") String nameortel);

    int alterAttribute(Map<String, Object> params);

    int countByTenant(@Param("t_id") long t_id);
}
