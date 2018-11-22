package com.hz.learnboot.security.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @author hezhao
 * @version v01.00.00 $Revision$
 * @date 2015年4月17日
 * @time 下午5:15:50
 */
public class MD5Util {

	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**此字符串相当于加密用的串，数据校验时结果不会被猜测。*/
	private static final String default_key = "dAA%D#V*2a9r4I!V";
	
	private MD5Util(){
		// 私有类构造方法
	}

	/**
	 * 对字符串进行MD5加密
	 *
	 * @author hezhao
	 * @param s
	 * @return
	 */
	public static final String md5(String s) {
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对字符串自行2次MD5加密MD5(MD5(s))
	 * 
	 * @author hezhao
	 * @param s
	 * @return
	 */
	public static final String md5x2(String s) {
		return md5(md5(s));
	}

	/**
	 * 使用MD5 对两端加密后的密文进行比较
	 * 
	 * @author hezhao
	 * @Time 2017年7月31日 下午4:30:11
	 * @param strOne
	 *            未加密的字符串
	 * @param strTwo
	 *            已加密的字符串
	 * @return boolean
	 */
	public static final boolean check(String strOne, String strTwo) {
		if (md5(strOne).equals(strTwo)) {
			return true;
		} else {
			return false;
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	/**
	 * 对字符串进行MD5加密,需要提供秘钥
	 *
	 * @param str 源字符串
	 * @param key 加密密钥
	 * @return 经过编码后的字符串
	 */
	public static String hash(String str, String key) {
		String input = str + key;
		return md5(input);
	}

	/**
	 * 对字符串进行MD5加密，使用默认秘钥
	 *
	 * @param str 源字符串
	 * @return hash值
	 */
	public static String hash(String str) {
		return hash(str, default_key);
	}

	/**
	 * 使用MD5 对两端hash加密后的密文进行比较
	 *
	 * @author hezhao
	 * @Time 2018年5月11日 下午4:30:11
	 * @param strOne
	 *            未加密的字符串
	 * @param key
	 *            加密秘钥
	 * @param strTwo
	 *            已加密的字符串
	 * @return boolean
	 */
	public static final boolean checkHash(String strOne, String key, String strTwo) {
		if (hash(strOne,key).equals(strTwo)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			NoSuchAlgorithmException {
		System.out.println(MD5Util.md5("admin"));
		System.out.println(MD5Util.md5("加密"));
		System.out.println(MD5Util.md5("20121lkkfaoisdfO$^#@!221"));
		System.out.println(MD5Util.md5("liangan0923A"));
		System.out.println(MD5Util.check("admin","21232F297A57A5A743894A0E4A801FC3"));


		System.out.println(hash("admin","123"));
		System.out.println(checkHash("admin","123","0192023A7BBD73250516F069DF18B500"));
	}

}
