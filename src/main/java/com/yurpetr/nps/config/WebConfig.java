package com.yurpetr.nps.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/webjars/**")
            .addResourceLocations("/webjars/");
      registry.addResourceHandler(
            "/img/**",
            "/css/**",
            "/fonts/**",
            "/libs/**",
            "/nps.webmanifest")
            .addResourceLocations(
                  "classpath:/static/img/",
                  "classpath:/static/css/",
                  "classpath:/static/fonts/",
                  "classpath:/static/libs/",
                  "classpath:/static/nps.webmanifest");
   }
}