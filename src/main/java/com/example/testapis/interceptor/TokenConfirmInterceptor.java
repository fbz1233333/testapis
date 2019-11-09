package com.example.testapis.interceptor;

import com.example.testapis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenConfirmInterceptor implements HandlerInterceptor {
    @Resource
    RedisUtil redisUtil;

    static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        String id=request.getHeader("UID");
        logger.info("Authorization:{}",token);
        logger.info("UID:{}",id);
        if (id==null || token==null){
            logger.info("未授权");
        }else if (redisUtil.count(id,token) ){
            logger.info("token验证成功");
        }else {
            logger.info("未登录");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
