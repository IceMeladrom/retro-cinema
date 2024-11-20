package ru.bmstu.retro_cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Настроим Spring обслуживать файлы из папки uploads
        registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
    }
}

