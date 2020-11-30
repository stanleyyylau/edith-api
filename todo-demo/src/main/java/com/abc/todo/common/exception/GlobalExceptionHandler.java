package com.abc.todo.common.exception;

import cn.hutool.core.util.StrUtil;
import com.anton.edith.autoconfigure.bean.Code;
import com.abc.todo.common.exception.http.HttpException;
import com.abc.todo.vo.UnifyResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.anton.edith.autoconfigure.util.RequestUtil.getSimpleRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size:20M}")
    private String maxFileSize;

    /**
     * HttpException
     */
    @ExceptionHandler({HttpException.class})
    public UnifyResponseVO processException(HttpException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO unifyResponse = new UnifyResponseVO();
        unifyResponse.setRequest(getSimpleRequest(request));
        int code = exception.getCode();
        boolean messageOnly = exception.isMessageOnly();
        unifyResponse.setCode(code);
        response.setStatus(exception.getHttpCode());
        String errorMessage = "error";
        if (StrUtil.isBlank(errorMessage) || messageOnly) {
            unifyResponse.setMessage(exception.getMessage());
        } else {
            unifyResponse.setMessage(errorMessage);
        }
        return unifyResponse;
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public UnifyResponseVO processException(ConstraintViolationException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        Map<String, Object> msg = new HashMap<>();
//        exception.getConstraintViolations().forEach(constraintViolation -> {
//            String template = constraintViolation.getMessage();
//            String path = constraintViolation.getPropertyPath().toString();
//            msg.put(StrUtil.toUnderlineCase(path), template);
//        });
        UnifyResponseVO unifyResponse = new UnifyResponseVO();
        unifyResponse.setRequest(getSimpleRequest(request));
        unifyResponse.setMessage(msg);
        unifyResponse.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return unifyResponse;
    }

    /**
     * NoHandlerFoundException
     */
    @ExceptionHandler({NoHandlerFoundException.class})
    public UnifyResponseVO processException(NoHandlerFoundException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO unifyResponse = new UnifyResponseVO();
        unifyResponse.setRequest(getSimpleRequest(request));
        String message = "error";
        if (StrUtil.isBlank(message)) {
            unifyResponse.setMessage(exception.getMessage());
        } else {
            unifyResponse.setMessage(message);
        }
        unifyResponse.setCode(Code.NOT_FOUND.getCode());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return unifyResponse;
    }

    /**
     * MissingServletRequestParameterException
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public UnifyResponseVO processException(MissingServletRequestParameterException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));

        String errorMessage = "eeor";
        if (StrUtil.isBlank(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(errorMessage + exception.getParameterName());
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * MethodArgumentTypeMismatchException
     */
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public UnifyResponseVO processException(MethodArgumentTypeMismatchException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = "eer";
        if (StrUtil.isBlank(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(exception.getValue() + errorMessage);
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * ServletException
     */
    @ExceptionHandler({ServletException.class})
    public UnifyResponseVO processException(ServletException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(exception.getMessage());
        result.setCode(Code.FAIL.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * MethodArgumentNotValidException
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public UnifyResponseVO processException(MethodArgumentNotValidException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        BindingResult bindingResult = exception.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        Map<String, Object> msg = new HashMap<>();
        errors.forEach(error -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                msg.put(StrUtil.toUnderlineCase(fieldError.getField()), fieldError.getDefaultMessage());
            } else {
                msg.put(StrUtil.toUnderlineCase(error.getObjectName()), error.getDefaultMessage());
            }
        });
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(msg);
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * HttpMessageNotReadableException
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public UnifyResponseVO processException(HttpMessageNotReadableException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = "err";
        if (StrUtil.isBlank(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(errorMessage);
        }
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * TypeMismatchException
     */
    @ExceptionHandler({TypeMismatchException.class})
    public UnifyResponseVO processException(TypeMismatchException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(exception.getMessage());
        result.setCode(Code.PARAMETER_ERROR.getCode());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return result;
    }

    /**
     * MaxUploadSizeExceededException
     */
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public UnifyResponseVO processException(MaxUploadSizeExceededException exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        String errorMessage = "erro";
        if (StrUtil.isBlank(errorMessage)) {
            result.setMessage(exception.getMessage());
        } else {
            result.setMessage(errorMessage + maxFileSize);
        }
        result.setCode(Code.FILE_TOO_LARGE.getCode());
        response.setStatus(HttpStatus.PAYLOAD_TOO_LARGE.value());
        return result;
    }

    /**
     * Exception
     */
    @ExceptionHandler({Exception.class})
    public UnifyResponseVO processException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        log.error("", exception);
        UnifyResponseVO result = new UnifyResponseVO();
        result.setRequest(getSimpleRequest(request));
        result.setMessage(Code.INTERNAL_SERVER_ERROR.getZhDescription());
        result.setCode(Code.INTERNAL_SERVER_ERROR.getCode());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return result;
    }

}
