package soochow.zmq.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soochow.zmq.filter.AuthFilter;

@Configuration
@EnableConfigurationProperties(AppProperties.class)
//@EnableWebSecurity
public class WebConfiguration  {

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

//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/mt/viewindex").hasRole("root")
//                .antMatchers("/mt/viewindex2").hasRole("person")
//                .antMatchers("/").hasRole("normal")
//        ;
//        //没有权限默认到登陆页面
//        http.formLogin().loginPage("/tologin");
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
//                withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("root","person","normal").and().
//                withUser("zmq").password(new BCryptPasswordEncoder().encode("zmq")).roles("person","normal");
//    }

}
