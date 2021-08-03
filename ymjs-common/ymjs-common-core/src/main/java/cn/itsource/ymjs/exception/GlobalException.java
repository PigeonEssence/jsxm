package cn.itsource.ymjs.exception;

import cn.itsource.ymjs.constants.ErrorCode;

public class GlobalException extends RuntimeException {

    private ErrorCode errorCode;
    public GlobalException(String errorMessage){
        super(errorMessage);
    }

    public GlobalException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
