//package com.daewon.daewon;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().permitAll();
////                .antMatchers("/api/hello").permitAll() // 해당 요청은 인증없이 할 수 있도록
////                .anyRequest().authenticated(); // 나머지는 인증이 필요하도록
//    }
//}
