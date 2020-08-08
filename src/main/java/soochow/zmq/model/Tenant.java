package soochow.zmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Tenant implements Serializable {
    private final static long serialVersionUID = 1L;

    private Long tenant_id;
    private String tenant_code;
    private String tenant_desc;
    private Integer tenant_priority;

    /**
     * 逻辑删除，删除时候，将该字段设置为N
     */
    private YesNo is_deleted = YesNo.N;

}
