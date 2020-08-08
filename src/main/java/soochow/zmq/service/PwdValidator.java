package soochow.zmq.service;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class PwdValidator {

    private final static Pattern PURE_NUMBER = Pattern.compile("\\d+");
    private final static Pattern PURE_LOWERCASE_CHAR = Pattern.compile("[a-z]+");
    private final static Pattern PURE_UPPER_CASE = Pattern.compile("[A-Z]+");


   public boolean isStrongEnough(String password) {
        return StringUtils.isNotBlank(password) && password.length() >= 6 && !PURE_NUMBER.matcher(password).matches()
                && !PURE_LOWERCASE_CHAR.matcher(password).matches() && !PURE_UPPER_CASE.matcher(password).matches();

    }
}
