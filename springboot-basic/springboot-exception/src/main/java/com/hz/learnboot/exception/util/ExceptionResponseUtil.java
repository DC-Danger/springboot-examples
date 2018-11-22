package com.hz.learnboot.exception.util;

/**
 * 异常响应信息工具类
 * 
 * @author hezhao
 * @date 2018年6月8日 上午9:25:33
 */
public class ExceptionResponseUtil {

	public static ExceptionResponse init(Integer code, String message) {
		return new ExceptionResponse(code, message);
	}

}
