package cn.muratjan.admin.common.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.muratjan.admin.common.AjaxResult;
import cn.muratjan.admin.common.constants.Constants;
import cn.muratjan.admin.common.constants.OperationCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author MRT
 * @date 2022/2/11 18:30
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AjaxResult bindExceptionHandler(BindException e) {
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", "缺少参数", error.getDefaultMessage());
        return AjaxResult.error(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public AjaxResult exceptionHandler(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldError();
        String message = String.format("%s:%s", "缺少参数", error.getDefaultMessage());
        return AjaxResult.error(message);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public AjaxResult exceptionHandler(HttpMessageNotReadableException e) {
        return AjaxResult.error("请求参数不合法");
    }

    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public AjaxResult notLoginExceptionHandler(NotLoginException e){
        AjaxResult ajaxResult = AjaxResult.error();
        ajaxResult.put(Constants.EXCEPTION_MESSAGE,e.getMessage());
        ajaxResult.put(Constants.OPERATION_CODE, OperationCode.NOT_LOGIN);
        return ajaxResult;
    }
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public AjaxResult missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e){
        AjaxResult ajaxResult = AjaxResult.error("缺少参数");
        ajaxResult.put(Constants.EXCEPTION_MESSAGE,e.getMessage());
        return ajaxResult;
    }

}
