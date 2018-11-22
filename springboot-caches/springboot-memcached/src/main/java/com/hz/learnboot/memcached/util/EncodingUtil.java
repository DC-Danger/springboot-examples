package com.hz.learnboot.memcached.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换工具类
 *
 * Created by hezhao on 2018-08-03 18:36
 */
public class EncodingUtil {

    private static final Logger logger = LoggerFactory.getLogger(EncodingUtil.class);

    private EncodingUtil(){
        // 私有类构造方法
    }

    /**
     * ISO-8859-1 to UTF-8
     *
     * @author hezhao
     * @Time 2017年7月28日 下午9:03:35
     * @param convertString
     * @return
     */
    public static String convertEncoding(String convertString) {
        String str = convertString;
        if (convertString != null) {
            try {
                // 判断当前字符串的编码格式
                if (convertString.equals(new String(convertString
                        .getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    str = new String(convertString.getBytes("ISO-8859-1"),
                            "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                logger.error(e.toString(), e);
            }
        }
        return str;
    }

}
