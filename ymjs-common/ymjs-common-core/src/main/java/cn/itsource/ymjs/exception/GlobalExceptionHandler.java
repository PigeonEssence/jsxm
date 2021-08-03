package cn.itsource.ymjs.exception;


import cn.itsource.ymjs.result.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public JSONResult globalException(GlobalException e ){
        log.error(e.getMessage());
        e.printStackTrace();
        return JSONResult.error().setMessage(e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public JSONResult exception(Exception e ){
        log.error(e.getMessage());
        e.printStackTrace();
        return JSONResult.error().setMessage("系统内部异常["+e.getMessage()+"]");
    }
}
