package com.hz.learnboot.jsonp.util;

import com.alibaba.fastjson.JSONObject;

/** 返回值
 * @Author hezhao
 * @Time 2018-07-08 0:44
 */
public class ResultUtil {
    private static final JSONObject resultJson = new JSONObject();

    public static Object success(Object obj) {
        resultJson.put("RESCODE", "000000");
        resultJson.put("RESMSG", obj != null ? obj : "操作成功");
        return resultJson;
    }

    public static JSONObject error(String msg){
        resultJson.put("RESCODE", "000001");
        resultJson.put("RESMSG", (msg != null && msg.trim().length() > 0) ? msg : "操作失败");
        return resultJson;
    }

    public static JSONObject error(String msg, String code){
        resultJson.put("RESCODE", code);
        resultJson.put("RESMSG", (msg != null && msg.trim().length() > 0) ? msg : "操作失败");
        return resultJson;
    }
}
