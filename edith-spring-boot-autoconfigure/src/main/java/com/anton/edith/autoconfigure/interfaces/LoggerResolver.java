package com.anton.edith.autoconfigure.interfaces;

import com.anton.edith.core.annotation.Logger;
import com.anton.edith.core.annotation.PermissionMeta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoggerResolver {

    /**
     * 处理
     *
     * @param meta     路由信息
     * @param logger   logger 信息
     * @param request  请求
     * @param response 响应
     */
    void handle(PermissionMeta meta, Logger logger, HttpServletRequest request, HttpServletResponse response);
}
