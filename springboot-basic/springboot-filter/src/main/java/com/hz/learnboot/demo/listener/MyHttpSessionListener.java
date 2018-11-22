package com.hz.learnboot.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听Session的创建与销毁
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {

	private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("HttpSession 被创建...");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("HttpSession 被销毁...");
	}

}
