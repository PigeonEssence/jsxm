package cn.itsource.ymjs.result;

import lombok.Data;

@Data
public class JSONResult<T> {
    //用户已经存在
    public static final int CODE_USER_EXIST = 1000;
    //未登录
    public static final int CODE_UN_LOGIN = 1001;
    //需要绑定微信
    public static final int CODE_UN_BIND_WECHART = 1002;
    public static final int CODE_LOGIN_SUCCESS = 1003;

    public static final int CODE_OK = 200;

    private boolean success = true;
    private String message = "成功";
    private T data;
    private Integer code = 200;

    //有数据失败结果
    public static JSONResult error(Object data){
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(false);
        jsonResult.setData(data);
        return jsonResult;
    }

    //无数据失败结果
    public static JSONResult error(){
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    //有数据成功结果
    public static JSONResult success(Object data){
        JSONResult jsonResult = new JSONResult();
        jsonResult.setData(data);
        return jsonResult;
    }

    //无数据成功结果
    public static JSONResult success(){
        return new JSONResult();
    }

    public JSONResult() {
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }


    public JSONResult setMessage(String message) {
        this.message = message;
        return this;
    }


    public JSONResult setData(T data) {
        this.data = data;
        return this;
    }

    public JSONResult setCode(Integer code) {
        this.code = code;
        return this;
    }

}
