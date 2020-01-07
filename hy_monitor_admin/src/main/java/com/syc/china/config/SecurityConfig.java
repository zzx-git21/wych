package com.syc.china.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 *
 */
//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String applicationPath;

    public SecurityConfig(AdminServerProperties properties) {
        //properties.getContextPath()--->http://localhost:8764/
        this.applicationPath = properties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //带有保存功能的请求处理器
        SavedRequestAwareAuthenticationSuccessHandler successHandler=new SavedRequestAwareAuthenticationSuccessHandler();
        //采用重定向的跳转策略
        successHandler.setTargetUrlParameter("redirectTo");

        //assets/**
        http.authorizeRequests()
                .antMatchers(applicationPath + "/assets/**")
                .permitAll() //对以上资源直接放行
                .antMatchers(applicationPath+"/login")
                .permitAll()
                .anyRequest()
                .authenticated() //其他任意的请求都必须经过认证
                .and()
                .formLogin()
                .loginPage(applicationPath+"/login")//登录界面
                .successHandler(successHandler)//登录成功后,机芯重定向
                .and()
                .logout()
                .logoutUrl(applicationPath+"/logout")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable();
    }

}
