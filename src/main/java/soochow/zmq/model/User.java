package soochow.zmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private Long u_id;
    /**
     * 所属租户
     */
    private Long owned_tenant;
    private String u_name;
    private String u_real_name;
    private String pwd;
    private AccountStatus u_status;
    private String u_describe;
    private String u_phone;
    private String u_email;
    private Date u_creatime;
    private String u_extend;

    public String validate() {
        //todo 校验各个字段的有效性，是否为空等。如果校验失败，返回失败原因描述。否则返回null

        return null;
    }

}
