package com.example.testapis.interceptor;

import com.example.testapis.annotiation.UserLoginToken;
import com.example.testapis.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class TokenConfirmInterceptor implements HandlerInterceptor {
    @Resource
    RedisUtil redisUtil;

    static Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(UserLoginToken.class)){
            String token=request.getHeader("token");
            String id=request.getHeader("UID");
            logger.info("token:{}",token);
            logger.info("UID:{}",id);
          if (redisUtil.count(id,token) ){
                logger.info("token验证成功");
                return true;
            }else {
                logger.info("验证失败,无权限");
                response.sendRedirect("/api/user/NoToken");
                return  false;
            }
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
