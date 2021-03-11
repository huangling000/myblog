package com.zero.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 拦截有Controller注解的控制器，如index
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    //拿到日志对象
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * return：控制返回一个页面，里加一些后台输出到前台的信息
     * @ExceptionHandler:表示方法可以做异常处理，后面参数表示只要是Exception级别的都可以
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception{
        logger.error("Request URL : {}, Exception : {}",request.getRequestURL(),e);
        //判断异常是否自定义被指定，如果被指定则不为空，抛出被指定异常
        if (AnnotationUtils.findAnnotation(e.getClass(),
                ResponseStatus.class) != null) {
            throw e;
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("Exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
