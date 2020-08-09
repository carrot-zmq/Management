package soochow.zmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
public class User {
    private final static Pattern EMAIL_PATTERN = Pattern.compile("[A-Za-z\\d\\u4e00-\\u9fa5]+@[a-zA-Z\\d_-]+(\\.[a-zA-Z\\d_-]+)+");
    private final static Pattern PHONE_PATTERN = Pattern.compile("1\\d{10}");

    private Long u_id;
    /**
     * 所属租户
     */
    private Long owned_tenant;
    private String u_name;
    private String u_real_name;
    private String pwd;
    private AccountStatus u_status = AccountStatus.Frozen;
    private String u_describe;
    private String u_phone;
    private String u_email;
    private Date u_create_time;
    private YesNo is_deleted;
    private String u_modified_by;
    private String u_extend;

    public String validate() {
        //todo 校验各个字段的有效性，是否为空等。如果校验失败，返回失败原因描述。否则返回null
        if (StringUtils.isBlank(u_email) || !EMAIL_PATTERN.matcher(u_email).matches()) {
            return "邮箱格式不正确";
        }
        if (StringUtils.isBlank(u_phone) || !PHONE_PATTERN.matcher(u_phone).matches()) {
            return "手机号格式不正确";
        }
        if (StringUtils.isBlank(u_name)) {
            return "账号名不能为空";
        }
        if (StringUtils.isBlank(u_real_name)) {
            return "真实姓名不能为空";
        }


        return null;
    }

    /**
     * 正规化 user 对象
     */
    public void normalize() {
        if (null == u_create_time) {
            u_create_time = new Date();
        }
        //this.u_real_name = this.u_real_name.trim();
    }

}
