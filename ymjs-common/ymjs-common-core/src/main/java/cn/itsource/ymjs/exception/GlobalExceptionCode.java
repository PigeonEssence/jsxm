package cn.itsource.ymjs.exception;

public enum  GlobalExceptionCode{

    BUSINESS_ERROR("1000","业务异常");

    private final String code;
    private final String msg;

    GlobalExceptionCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg+"["+code+"]";
    }
}
