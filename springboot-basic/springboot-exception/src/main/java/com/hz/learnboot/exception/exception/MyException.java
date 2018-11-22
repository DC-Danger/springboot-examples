package com.hz.learnboot.exception.exception;

/**
 * 自定义的异常类
 * 
 * @author hezhao
 * @date 2018年6月8日 上午9:27:29
 */
public class MyException extends Exception {

	private static final long serialVersionUID = 3720658094018235765L;

	private Integer code;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

	public MyException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
