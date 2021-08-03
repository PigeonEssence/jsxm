package cn.itsource.ymjs.utils;



import cn.itsource.ymjs.constants.ErrorCode;
import cn.itsource.ymjs.exception.GlobalException;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertUtil {

    //座机的正则表达式
    private static final Pattern CHINA_PATTERN_TEL = Pattern.compile("^(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}$");

    //手机的正则表达式
    private static final Pattern CHINA_PATTERN_PHONE = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");

    //邮箱的正则表达式
    private static final Pattern CHINA_PATTERN_EMAIL = Pattern.compile("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
    /**--------------------------------------------------------
     座机断言
     --------------------------------------------------------**/
    public static void isTel(String tel,String message){
        Matcher t= CHINA_PATTERN_TEL.matcher(tel);
        if(!t.matches()){
            throw new GlobalException(message);
            
        }
    }

    /**--------------------------------------------------------
     手机号断言
     --------------------------------------------------------**/
    public static void isPhone(String phone,String message){
        Matcher m = CHINA_PATTERN_PHONE.matcher(phone);
        if(!m.matches()){
            throw new GlobalException(message);
        }
    }
    /**--------------------------------------------------------
     邮箱断言
     --------------------------------------------------------**/
    public static void isEmail(String email,String message){
        Matcher n = CHINA_PATTERN_EMAIL.matcher(email);
        if(!n.matches()){
            throw new GlobalException(message);
        }
    }

    public static void isNotEmpty(String text, String message){
        if(!StringUtils.hasLength(text)){
            throw new GlobalException(message);
        }
    }

    public static void isNotEmpty(String text, ErrorCode errorCode) {
        if (!StringUtils.hasLength(text)) {
            throw new GlobalException(errorCode);
        }
    }

    public static void isNull(Object obj , String message){
        if(obj != null){
            throw new GlobalException(message);
        }
    }

    public static  void isFalse(boolean msg,String message){
        if(msg==true){
            throw new GlobalException(message);
        }
    }

    /**=================================================
     * 方法说明:字符串一致
     =================================================== **/
    public static void isEqules(String message1,String message2, String message3){
        if(!message1.equals(message2)){
            throw new GlobalException(message3);
        }
    }
    public static void isNotEqules(String message1,String message2, String message3){
        if(!message1.equals(message2)){
            throw new GlobalException(message3);
        }
    }
    public static void isNotNull(Object obj, String message3){
        if(obj == null){
            throw new GlobalException(message3);
        }
    }

    public static void isTrue(boolean isTrue , String message){
        if(!isTrue){
            throw new GlobalException(message);
        }
    }
}
