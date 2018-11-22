package com.hz.learnboot.task.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印日志工具类
 *
 * Created by hezhao on 2018-07-11 20:12
 */
public class LoggerUtil {

    private static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    // 打印内容，带时间
    public static void info(String msg){
        logger.info(sdf.format(new Date()) + " | " + msg);
    }

    public static void error(String msg){
        logger.error(sdf.format(new Date()) + " | " + msg);
    }
}
