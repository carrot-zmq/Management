package soochow.zmq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.service.PwdValidator;

@Configuration
public class UserConfiguration {

    @Bean
    public PwdValidator pwdValidator() {
        return new PwdValidator();
    }



}
