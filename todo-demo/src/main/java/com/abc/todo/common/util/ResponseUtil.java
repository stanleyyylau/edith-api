package com.abc.todo.common.util;

import com.anton.edith.autoconfigure.bean.Code;
import com.abc.todo.vo.PageResponseVO;
import com.abc.todo.vo.UnifyResponseVO;
import com.anton.edith.autoconfigure.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class ResponseUtil {

    /**
     * 获得当前响应
     *
     * @return 响应
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void setCurrentResponseHttpStatus(int httpStatus) {
        getResponse().setStatus(httpStatus);
    }

    public static <T> UnifyResponseVO<T> generateCreatedResponse(int code) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(Code.CREATED.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateCreatedResponse(int code, T data) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateDeletedResponse(int code) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(Code.SUCCESS.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateDeletedResponse(int code, T data) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateUpdatedResponse(int code) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(Code.SUCCESS.getDescription())
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateUpdatedResponse(int code, T data) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .message(data)
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static <T> UnifyResponseVO<T> generateUnifyResponse(int code) {
        return (UnifyResponseVO<T>) UnifyResponseVO.builder()
                .code(code)
                .request(RequestUtil.getSimpleRequest())
                .build();
    }

    public static PageResponseVO generatePageResult(int total, List items, int page, int count) {
        return new PageResponseVO(total, items, page, count);
    }
}
