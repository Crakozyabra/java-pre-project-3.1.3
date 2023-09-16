package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        // https://docs.spring.io/spring-security/site/docs/4.2.20.RELEASE/guides/html5/form-javaconfig.html#configuring-a-custom-login-page
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/user").setViewName("user");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
