package com.hz.learnboot.response.result;

/**
 * 应用系统级别的错误码
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface{

    // 系统公用返回码
    SUCCESS("000000", "成功"),
    ERROR("000001", "系统异常"),

    // 业务公用返回码
    PARAMS_MISSING("100000", "请求参数不全"),
    AUTHORITY_ERROR("100001", "无权限"),
    USER_NOT_EXIST("100002", "用户不存在"),
    USER_INFO_IMPERFECT("100003", "用户信息不完整"),
    USER_NOT_LOGIN("100004", "用户未登录"),
    PARAMS_ILLEGAL("100005", "请求参数非法"),
    REQUEST_FAIL("100006", "请求失败"),
    REQUEST_FILE_NOT_FOUND("100007", "未找到文件");

    private String code;

    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage(){
        return this.message;
    }
}
