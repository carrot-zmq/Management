package soochow.zmq.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.filter.AuthFilter;

import java.util.Collections;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class WebConfiguration {

    @Autowired
    AppProperties appProperties;


    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter(appProperties.getAnonymousUrls(), appProperties.getLoginUrl());
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistrationBean(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>(authFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
