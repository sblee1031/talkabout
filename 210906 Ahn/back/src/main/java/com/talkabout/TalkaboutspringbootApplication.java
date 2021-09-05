package com.talkabout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TalkaboutspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalkaboutspringbootApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowCredentials(true).allowedOriginPatterns("http://localhost:8888","http://localhost:3000","http://localhost:9999").allowedMethods("*");
//            }
//        };
//    }
}
