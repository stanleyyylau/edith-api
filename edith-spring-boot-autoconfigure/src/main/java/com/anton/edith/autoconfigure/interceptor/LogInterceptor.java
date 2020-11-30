package com.anton.edith.autoconfigure.interceptor;

import com.anton.edith.core.annotation.Logger;
import com.anton.edith.core.annotation.PermissionMeta;
import com.anton.edith.autoconfigure.interfaces.LoggerResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private LoggerResolver loggerResolver;

    /**
     * 后置处理
     *
     * @param request      请求
     * @param response     响应
     * @param handler      处理器
     * @param modelAndView 视图
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 记录日志
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Logger logger = method.getAnnotation(Logger.class);
            if (logger != null) {
                PermissionMeta meta = method.getAnnotation(PermissionMeta.class);
                // parse template and extract properties from request,response and modelAndView
                loggerResolver.handle(meta, logger, request, response);
            }
        }
    }
}
