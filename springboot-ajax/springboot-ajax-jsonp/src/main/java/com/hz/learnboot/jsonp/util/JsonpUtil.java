package com.hz.learnboot.jsonp.util;

import com.alibaba.fastjson.JSON;

/**
 * JsonP工具类
 *
 * @Author hezhao
 * @Time 2018-07-07 23:52
 */
public class JsonpUtil {

    private static final String defaultCallback = "callback";

    /**
     * 用于做JSONP回调函数拼接
     * @param callback 回调函数
     * @param result 返回结果
     * @return 返回拼接后的字符串
     */
    public static String callbackStr(String callback, String result){
        if(callback == null || callback.trim().length() == 0){
            callback = defaultCallback;
        }
        // 返回字符串拼接
        return callback+"("+result+");";
    }

    /**
     * 用于做JSONP回调函数拼接，回调函数默认为callback
     * @param result 返回结果
     * @return 返回拼接后的字符串
     */
    public static String callbackStr(String result){
        return callbackStr(defaultCallback, result);
    }

    /**
     * 用于做JSONP回调函数拼接
     * @param callback 回调函数
     * @param result 返回结果对象，将会转换为JSON字符串
     * @return 返回拼接后的字符串
     */
    public static String callbackStr(String callback, Object result){
        String resultStr = "";
        if(result != null) {
            resultStr = JSON.toJSONString(result);
        }
        return callbackStr(callback, resultStr);
    }

    /**
     * 用于做JSONP回调函数拼接，回调函数默认为callback
     * @param result 返回结果对象，将会转换为JSON字符串
     * @return 返回拼接后的字符串
     */
    public static String callbackStr(Object result){
        return callbackStr(defaultCallback, result);
    }

}
