package soochow.zmq.model.validate;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static soochow.zmq.model.validate.ValidateUtil.validateEmail;

public class ValidateUtilTest {

    @Test
    public void testValidateEmail_empty() {
        boolean result = ValidateUtil.validateEmail("");
        assertThat(result).isFalse();
        result = validateEmail(null);
        assertThat(result).isFalse();
        assertThat(validateEmail("   ")).isFalse();
        assertThat(validateEmail("   \t")).isFalse();
        assertThat(validateEmail("   \n\n")).isFalse();
    }

    @Test
    public void testValidateEmail_legal() {
        assertThat(validateEmail("123@123.xxx")).isTrue();
        assertThat(validateEmail("12345中华@163.com")).isTrue();
        assertThat(validateEmail("苏州园林@163.com")).isTrue();

    }

    @Test
    public void testValidateEmail_nonIllegal() {
        assertThat(validateEmail("123")).isFalse();
        assertThat(validateEmail("12345@")).isFalse();
        assertThat(validateEmail("123@163")).isFalse();
        assertThat(validateEmail("@163")).isFalse();
        assertThat(validateEmail("123@163.中华")).isFalse();
        assertThat(validateEmail("123@mq.中华")).isFalse();
    }
}
