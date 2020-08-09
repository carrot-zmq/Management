package soochow.zmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Tenant implements Serializable {
    private final static long serialVersionUID = 1L;

    private Long t_id;
    private String t_name;
    private String t_describe;
    private String t_type;
    private Date t_create_time;
    /**
     * 逻辑删除，删除时候，将该字段设置为N
     */
    private YesNo is_deleted = YesNo.N;
    private Integer t_priority;

    public String validate() {
        //todo 校验各个字段的有效性，是否为空等。如果校验失败，返回失败原因描述。否则返回null
        if (StringUtils.isBlank(t_name) ) {
            return "租户姓名不能为空";
        }
        if (StringUtils.isBlank(t_type) ) {
            return "租户类型不能为空";
        }

        return null;
    }

    /**
     * 正规化 user 对象
     */
    public void normalize() {
        if (null == t_create_time) {
            t_create_time = new Date();
        }
        //this.u_real_name = this.u_real_name.trim();
    }

}
