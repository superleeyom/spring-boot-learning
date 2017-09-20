package com.leeyom.web;

import com.leeyom.Exception.MyException;
import com.leeyom.pojo.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    /*
        1. @ControllerAdvice：定义一个统一的异常处理类。
        2. @ExceptionHandler：用来定义函数针对的异常类型。这里定义的是顶级的异常父类 Execption，也就是只要其他的 handler 里面的有抛出任何的异常，
        都会触发这个方法，然后将其映射到 error.html页面中，也可以根据抛出的具体Exception类型匹配@ExceptionHandler中配置的异常类型来匹配错误映射和处理。
     */

    /**
     * 定义一个全局的异常处理类
     * @param request
     * @param e
     * @return 将异常信息映射到 error.html 页面中
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView m = new ModelAndView();
        m.addObject("exception", e);
        m.addObject("url", request.getRequestURI());
        m.setViewName(DEFAULT_ERROR_VIEW);
        return m;
    }

    /**
     * 定义一个针对 MyException 异常的处理类，返回 json 格式数据
     * @param request
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MyException.class)
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest request,MyException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(request.getRequestURL().toString());
        return r;
    }
}
