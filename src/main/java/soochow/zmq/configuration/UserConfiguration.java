package soochow.zmq.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.service.*;
import soochow.zmq.service.impl.AuthenticationServiceImpl;
import soochow.zmq.service.impl.RoleQueryServiceImpl;
import soochow.zmq.service.impl.UserManageServiceImpl;
import soochow.zmq.service.impl.UserQueryServiceImpl;


//指示一个类声明一个或多个{@Bean}方法，并且可以由Spring容器在运行时为这些Bean生成Bean定义和服务请求
@Configuration
public class UserConfiguration {

    //指示方法产生一个由spring容器管理的bean
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
