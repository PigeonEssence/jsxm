package cn.itsource.ymjs.exception;

public class GlobalException extends RuntimeException {

    public GlobalException(String errorMessage){
        super(errorMessage);
    }
}
