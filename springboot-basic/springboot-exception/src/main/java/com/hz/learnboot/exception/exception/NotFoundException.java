package com.hz.learnboot.exception.exception;

/**
 * 模拟404错误处理异常类
 * 
 * @author hezhao
 * @date 2018年6月8日 上午9:27:29
 */
public class NotFoundException extends Exception {

	private static final long serialVersionUID = -3337173015423201991L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

}
