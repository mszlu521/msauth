package com.mszlu.msauth.admin.handler;

import com.mszlu.msauth.response.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 对加了@Controller注解的方法进行拦截处理 AOP的实现
 * @author mszlu
 */
@ControllerAdvice
public class AllExceptionHandler {
    /**
     * 进行异常处理，处理Exception.class的异常
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception ex){
        ex.printStackTrace();
        return Result.fail(-999,"系统异常");
    }

}