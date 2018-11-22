package com.hz.learnboot.limit.exception;

/**
 * 自定义异常类，用来处理URL攻击时产生的异常
 *
 * Created by hezhao on 2018-07-24 11:39
 */
public class RequestLimitException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }

}
