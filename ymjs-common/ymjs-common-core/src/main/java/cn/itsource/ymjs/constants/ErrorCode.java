package cn.itsource.ymjs.constants;

public enum ErrorCode {
    /**=================================================
     * 方法说明:相当于是在调用构造器
     =================================================== **/
    OK("1000","成功"),

    LOGIN_SUCCESS("1008","登录成功"),

    TO_BOUND("1009","需要绑定"),

    ID_IS_NULL("1002","ID不可为空"),

    DEPT_NAME_IS_NULL("1003","部门名字不可为空"),

    DEPT_SN_IS_NULL("1004","部门编号不可为空"),

    SHOP_NAME_IS_NULL("1005","店铺名不可为空"),

    USER_PHONE_NOT_EXIST("1006","手机号不存在"),

    USER_PHONE_EXIST("1007","手机号已存在"),

    SYSTEM_ERROR("1001","系统内部异常"),

    TOKEN_NOT_EXIST("1008","未登录");
    /**=================================================
     * 方法说明:生成构造器
     =================================================== **/

    private String code;
    private String message;

    ErrorCode(String code, String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }



}
