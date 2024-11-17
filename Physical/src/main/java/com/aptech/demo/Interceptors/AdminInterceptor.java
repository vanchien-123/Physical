package com.aptech.demo.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object acc = request.getSession().getAttribute("myacc");
        if (acc == null) {
            Logger log = Logger.getGlobal();
            log.info("Tài khoản chưa tồn tại");
            response.sendRedirect("/admin/login");
            return false;
        } else {
            Logger log = Logger.getGlobal();
            log.info("Tài khoản đã tồn tại");
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}


