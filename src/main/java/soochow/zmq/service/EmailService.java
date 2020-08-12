package soochow.zmq.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class EmailService {

    final static String PROPERTIES_FILE = "mail.properties";
    final static String USER_NAME_KEY = "username";
    final static String PASSWORD_KEY = "password";
    final static String FROM_ADDRESS_KEY = "from.address";

    private final Properties prop = new Properties();

    private Authenticator emailAuthenticator;

    private String fromAddress;

    @PostConstruct
    public void init() throws IOException {
        //下载配置文件中的内容，如果不存在该字段的话：报IllegalArgumentException错误
        Resource resource = new ClassPathResource(PROPERTIES_FILE);
        prop.load(resource.getInputStream());
        checkKey(USER_NAME_KEY);
        checkKey(PASSWORD_KEY);
        checkKey(FROM_ADDRESS_KEY);

        String username = String.valueOf(prop.remove(USER_NAME_KEY));
        String password = String.valueOf(prop.remove(PASSWORD_KEY));
        fromAddress = String.valueOf(prop.remove(FROM_ADDRESS_KEY));
        if (StringUtils.isBlank(fromAddress)) {
            throw new IllegalArgumentException(FROM_ADDRESS_KEY + " in " + PROPERTIES_FILE + "值无效");
        }

        emailAuthenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };

    }

    private void checkKey(String key) {
        if (!prop.containsKey(key)) {
            throw new IllegalArgumentException(PROPERTIES_FILE + " 没有配置" + key);
        }
    }

    public boolean send(String subject, String toAddress, String content) {

        Session session = Session.getInstance(prop, emailAuthenticator);
        try {


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(StringUtils.isEmpty(subject) ? "密码重置" : subject);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html; charset=UTF-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            return true;
        } catch (AddressException addressEx) {
            log.error("邮件地址无法识别", addressEx);
        } catch (MessagingException messagingEx) {
            log.error("邮件发送异常", messagingEx);
        }
        return false;
    }
}
