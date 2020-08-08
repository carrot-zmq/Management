package soochow.zmq.model.validate;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class ValidateUtil {

    private final static Pattern EMAIL_PATTERN = Pattern.compile(".+?@[a-zA-Z\\d_-]+(\\.[a-zA-Z\\d_-]+)+");

    /**
     * 校验 email 地址合法性
     * @param email
     * @return true 表示合法，false 表示非法
     */
    public static boolean validateEmail(String email) {
        return StringUtils.isNotBlank(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    public static void main(String... args) {
        boolean res = validateEmail("123@123.com.cn");
        System.out.println(res);
    }
}
