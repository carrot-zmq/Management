package soochow.zmq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.service.EmailService;

@Configuration
public class FrameworkConfiguration {

    @Bean
    public EmailService emailService() {
        return new EmailService();
    }
}
