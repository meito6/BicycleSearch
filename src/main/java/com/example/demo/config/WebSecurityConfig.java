package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.MyUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private MyUserService userService;
    
    @Autowired
    public WebSecurityConfig (MyUserService userService) {
        this.userService = userService;
    }
     
    // URLパス毎に制御
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
            .antMatchers("/js/**", "/css/**", "/loginForm", "/insert").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/loginForm")
            .loginProcessingUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/portfolio", true)
            .failureUrl("/loginForm?error=true");
    }
     
    // ユーザ情報の取得
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
 
    // パスワードハッシュ化する
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            return bcpe;
    }

}
