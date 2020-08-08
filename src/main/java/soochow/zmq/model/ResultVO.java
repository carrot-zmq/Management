package soochow.zmq.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultVO<T> {
    private boolean success = true;
    private String errorMsg;
    private T data;


    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = new ResultVO<T>();
        result.data = data;
        return result;
    }

    public static <T> ResultVO<T> fail(String errorMsg) {
        ResultVO<T> result = new ResultVO<T>();
        result.success = false;
        result.errorMsg = errorMsg;
        return result;
    }
}
