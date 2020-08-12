package soochow.zmq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.service.*;
import soochow.zmq.service.impl.AuthenticationServiceImpl;
import soochow.zmq.service.impl.RoleQueryServiceImpl;
import soochow.zmq.service.impl.UserManageServiceImpl;
import soochow.zmq.service.impl.UserQueryServiceImpl;

@Configuration
public class UserConfiguration {

    @Bean
    public PwdValidator pwdValidator() {
        return new PwdValidator();
    }


    @Bean
    public UserManageService userManageService() {
        return new UserManageServiceImpl();
    }


    @Bean
    public AuthenticationService authenticationService() {
        return new AuthenticationServiceImpl();
    }

    @Bean
    public UserQueryService userQueryService() {
        return new UserQueryServiceImpl();
    }

    @Bean
    public RoleQueryService roleQueryService(){
        return new RoleQueryServiceImpl();
    }



}
