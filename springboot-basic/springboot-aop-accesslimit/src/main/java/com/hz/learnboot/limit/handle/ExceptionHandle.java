package com.hz.learnboot.limit.handle;

import com.hz.learnboot.limit.exception.RequestLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理类<br>
 * 捕获程序所有异常，针对不同异常，采取不同的处理方式
 * 
 * @author hezhao
 * @date 2018年6月7日 下午4:55:59
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle {

	/**
	 * <strong>@ExceptionHandler</strong>指定需要捕获的异常类型<br>
	 * <strong>@ResponseStatus</strong>指定Http响应状态码：500<br>
	 * 捕获抛出的所有RequestLimitException异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RequestLimitException.class)
	public ModelAndView handleBadRequestException(RequestLimitException ex) {
		// 返回自定义错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("request_limit");
		return modelAndView;
	}

}
