package com.example.testapis.config;


import com.example.testapis.interceptor.TokenConfirmInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    @Value("${file.image.originPath}")
    String IMG_ORGIN_PATH;

    @Value("${file.image.toPath}")
    String IMG_TO_PATH;


    @Value("${url.basic}")
    String URL_BASIC;
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(IMG_ORGIN_PATH).addResourceLocations(IMG_TO_PATH);
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(tokenConfirmInterceptor()).addPathPatterns(URL_BASIC);
    }
    @Bean
    public TokenConfirmInterceptor tokenConfirmInterceptor(){
        return new TokenConfirmInterceptor();
    }

}
