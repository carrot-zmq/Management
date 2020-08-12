package soochow.zmq.service;

import org.junit.jupiter.api.Test;

public class EmailServiceTest {
    @Test
    public void testSendSuccess() throws Exception {
        EmailService emailService = new EmailService();
        emailService.init();
        emailService.send("找回密码", "410736810@qq.com", "请勿回复本邮件.点击下面的链接,重设密码<br/>< a href=www.baidu.com\"\n" +
                "         + \" target='_BLANK'>www.baidu.com\"\n" +
                "        + \"</ a>  或者    < a href=www.baidu.com\"\n" +
                "        + \" target='_BLANK'>点击我重新设置密码</ a>\"\n" +
                "        + \"<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'");
    }
}
