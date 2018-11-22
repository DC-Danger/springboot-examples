package com.hz.learnboot.exception.handle;

import com.hz.learnboot.exception.exception.BadRequestException;
import com.hz.learnboot.exception.exception.NotFoundException;
import com.hz.learnboot.exception.exception.MyException;
import com.hz.learnboot.exception.util.ExceptionResponse;
import com.hz.learnboot.exception.util.ExceptionResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

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
	 * 捕获抛出的所有SQLException异常
	 */
	@ExceptionHandler(SQLException.class)
	public ExceptionResponse handleSQLException(HttpServletRequest request, Exception ex) {
		return ExceptionResponseUtil.init(100001, "SQL异常！");
	}

	/**
	 * <strong>@ExceptionHandler</strong>指定需要捕获的异常类型<br>
	 * <strong>@ResponseStatus</strong>指定Http响应状态码：404<br>
	 * 捕获抛出的所有NotFoundException异常
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ExceptionResponse handleNotFoundException(NotFoundException ex) {
		return ExceptionResponseUtil.init(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	/**
	 * <strong>@ExceptionHandler</strong>指定需要捕获的异常类型<br>
	 * <strong>@ResponseStatus</strong>指定Http响应状态码：400<br>
	 * 捕获抛出的所有BadRequestException异常
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BadRequestException.class)
	public ExceptionResponse handleBadRequestException(BadRequestException ex) {
		return ExceptionResponseUtil.init(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}

	/**
	 * <strong>@ExceptionHandler</strong>指定需要捕获的异常类型<br>
	 * <strong>@ResponseStatus</strong>指定Http响应状态码：500<br>
	 * 捕获抛出的所有Exception异常<br>
	 * 其中又通过instanceof 判断具体的异常类型，针对异常类型做处理
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Object handleServerErrorException(HttpServletRequest request, Exception ex) {
		String message = ex.getMessage();

		// 自定义异常
		if (ex instanceof MyException) {
			MyException myEx = (MyException) ex;
			return ExceptionResponseUtil.init(myEx.getCode(), message);
		}

		// 返回自定义错误页面
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", message);
		modelAndView.addObject("url", request.getRequestURL());
		modelAndView.addObject("stackTrace", ex.getStackTrace());
		modelAndView.setViewName("error");
		return modelAndView;

		// return ExceptionResponseUtil.init(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

}
