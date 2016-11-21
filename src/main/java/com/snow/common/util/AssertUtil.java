package com.snow.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.util.Date;


/**
 * 数据校验工具，校验出错返回异常
 * 
 * @author zhouhui
 * @version $Id: AssertUtil.java, v 0.1 2015年3月27日 下午3:33:45 zhouhui Exp $
 */
public class AssertUtil {
	/**
	 * 判断对象不为空
	 *
	 * @param object
	 *            对象
	 * @param message
	 *            错误信息
	 * @throws RuntimeException
	 */
	public static void notNull(Object object, String message) throws RuntimeException {
		if (object == null) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param object
	 * @param message
	 * @throws RuntimeException
	 */
	public static void notEmpty(String object, String message) throws RuntimeException {
		if (StringUtils.isEmpty(object)) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 数据不能为零
	 * 
	 * @param num
	 * @param message
	 * @throws RuntimeException
	 */
	public static void notZero(Number num, String message) throws RuntimeException {
		if (num.intValue() == 0) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断日期格式是否是yyyyMMdd
	 * 
	 * @param date
	 * @param message
	 * @throws RuntimeException
	 */
	public static void isValidShortDateFormat(String date, String message)
			throws RuntimeException {
		notEmpty(date, message);
		if (!DateUtils.isValidShortDateFormat(date)) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断日期格式是否是yyyyMMddHHmmss
	 * 
	 * @param date
	 * @param message
	 * @throws RuntimeException
	 */
	public static void isValidLongDateFormat(String date, String message)
			throws RuntimeException {
		notEmpty(date, message);
		if (!DateUtils.isValidLongDateFormat(date)) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param num
	 * @param message
	 * @throws RuntimeException
	 */
	public static void isDigitsFormat(String num, String message)
			throws RuntimeException {
		notEmpty(num, message);
		if (!NumberUtils.isDigits(num)) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断时间区间 (两时间间的比较)
	 * 
	 * @param Date1
	 *            ：
	 * @param Date2
	 *            ：
	 * @param dayNum
	 *            ： 最大相差的天数
	 * @throws Exception 
	 * @throws Exception
	 */
	public static void compareDate(String Date1, String Date2, int dayNum,
			String message) throws Exception {
		notEmpty(Date1, "开始时间为空");
		notEmpty(Date2, "结束时间为空");
		if (DateUtils.getDiffDays(DateUtils.parseDateNewFormat(Date1),
				DateUtils.parseDateNewFormat(Date2)) > dayNum) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 当前时间 与开始时间比较
	 * 
	 * @param startDate
	 *            ：开始时间
	 * @param dayNum
	 *            ：最大的时间差距
	 * @param message
	 *            ：错误信息
	 * @throws Exception 
	 * @throws ParseException 
	 */
	public static void currentDate(String startDate, int dayNum, String message)
			throws ParseException, Exception {
		notEmpty(startDate, "开始时间为空");
		if (DateUtils.getDiffDays(new Date(),
				DateUtils.parseDateNoTime(startDate, DateUtils.NEW_FORMAT)) > dayNum) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 用于web 层的判断和异常信息
	 * 
	 * <pre>
	 *  逻辑说明：这里只做两个对象对比不相同时抛出异常
	 * </pre>
	 * 
	 * @param object1
	 * @param object2
	 * @param message
	 * @throws RuntimeException
	 */
	public static void isEquels(String object1, String object2, String message)
			throws RuntimeException {
		if (!StringUtils.equals(object1, object2)) {
			throw new RuntimeException(message);
		}
	}

	/**
	 * 判断是否超过指定的长度
	 * 
	 * @param object
	 * @param length
	 * @param message
	 * @throws RuntimeException
	 */
	public static void isPassLength(String object, int length, String message)
			throws RuntimeException {
		notEmpty(object, message);
		if (object.length() != length) {
			throw new RuntimeException(message);
		}

	}
}
