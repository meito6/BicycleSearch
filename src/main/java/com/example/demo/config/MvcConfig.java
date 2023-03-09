package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

public class MvcConfig {

	/**
     * 「/login」というURLからloginForm.htmlを呼び出す
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("loginForm");
    }
}
