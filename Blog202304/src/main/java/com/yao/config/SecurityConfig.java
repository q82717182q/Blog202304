package com.yao.config;

import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.authentication.*;

/*
 * @author Jack
 * @date 2023/3/31
 * */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**","/home").permitAll()
                .antMatchers(HttpMethod.POST,"/register", "/login").permitAll()
                .antMatchers(HttpMethod.POST,"/create").permitAll()
                .anyRequest().authenticated() //代表攔截所有api  //順序有影響，這個放在最後面，才不會permit不了其他請求
                .and()
                .csrf().disable()  // 沒有這一行的話post請求就算下面用antMatchers還是會被擋
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .and()
                .logout()
                .permitAll();
                ;
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/**")
                .antMatchers("/css/**", "/js/**", "/images/**");
    }

}

