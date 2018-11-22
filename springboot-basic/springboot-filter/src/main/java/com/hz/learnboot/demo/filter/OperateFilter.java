package com.hz.learnboot.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 操作过滤器，防止用户未登录进入后台
 * 
 * @author 
 */
//@WebFilter(filterName = "OperateFilter", urlPatterns = { "/*" })
@Order(2)
public class OperateFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 7711738258783809653L;

	private static final Logger logger = LoggerFactory.getLogger(OperateFilter.class);

	@Override
	public void init(FilterConfig config) {
		logger.info("OperateFilter 过滤器初始化...");
	}
	
	public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) resp;
		final HttpSession session = request.getSession(true);

//		// 取出用户session
//		final Object user = session.getAttribute("userInfo");
//		final String url = request.getRequestURI();
//
//		if (user == null) {
//			// 判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转
//			if (url != null && !url.equals("") && (url.indexOf("index") < 0 && url.indexOf("login") < 0 && url.indexOf("js") < 0 &&
//							url.indexOf("css") < 0 && url.indexOf("fonts") < 0 )) {
//				response.sendRedirect(request.getContextPath() + "/index.jsp");
//				return;
//			}
//		}

		logger.info("OperateFilter 正在访问的URI ::: " + request.getRequestURI());

		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		logger.info("OperateFilter 过滤器销毁...");
	}

}
