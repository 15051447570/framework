package com.snow.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 时间处理工具类
 * 
 * @author zhouhui
 * @version $Id: DateUtils.java, v 0.1 2015年3月27日 下午2:21:39 zhouhui Exp $
 */
public class DateUtils {

	/** 一天的秒数 */
	public final static long ONE_DAY_SECONDS = 86400;
	/** 一天的毫秒数 */
	public final static long ONE_DAY_MILL_SECONDS = 86400000;
	/** yyyyMMdd时间格式 */
	public final static String SHORT_FORMAT = "yyyyMMdd";
	/** yyyyMMddHHmmss时间格式 */
	public final static String LONG_FORMAT = "yyyyMMddHHmmss";
	/** 比long少秒的日期格式 yyyyMMddHHmm */
	public final static String LOWER_LONG_FORMAT = "yyyyMMddHHmm";
	/** yyyy-MM-dd时间格式 */
	public final static String WEB_FORMAT = "yyyy-MM-dd";
	/** HHmmss时间格式 */
	public final static String TIME_FORMAT = "HHmmss";
	/** yyyyMM时间格式 */
	public final static String MONTH_FORMAT = "yyyyMM";
	/** yyyy年MM月dd日时间格式 */
	public final static String CHINESE_DT_FORMAT = "yyyy年MM月dd日";
	/** yyyy-MM-dd HH:mm:ss时间格式 */
	public final static String NEW_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd HH:mm时间格式 */
	public final static String NO_SECOND_FORMAT = "yyyy-MM-dd HH:mm";
	/** yyyy时间格式 */
	public static String YEAR_FORMAT = "yyyy";
	/** yyyy年MM月dd日HH点mm分ss秒 */
	public final static String CHINESE_ALL_DATE_FORMAT = "yyyy年MM月dd日HH点mm分ss秒";
	/** HH:mm时间格式 */
	public final static String HOURS_FORMAT = "HH:mm";
	/** HH:mm时间格式 */
	public final static String SECOND_FORMAT = "HH:mm:ss";
	/** yyyy.MM.dd 时间格式 */
	public final static String DATE_PICKER_FORMAT = "yyyy.MM.dd";
	/** 零时零分零秒 */
	public static final String START_TIME = " 00:00:00";
	/** 23:59:59 */
	public static final String END_TIME = " 23:59:59";

	/**
	 * 得到新的时间格式
	 * 
	 * @param pattern
	 *            匹配表达式
	 * @return 新的时间格式
	 */
	public static DateFormat getNewDateFormat(String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);

		df.setLenient(false);
		return df;
	}

	/**
	 * 时间格式
	 * 
	 * @param date
	 *            格式化前的时间
	 * @param format
	 *            需要转换的格式
	 * @return 格式化后的时间
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 时间格式
	 * 
	 * @param dateStr
	 *            格式化前的时间
	 * @param format
	 *            需要转换的格式
	 * @return 格式化后的时间
	 * @throws ParseException
	 */
	public static Date formatToDate(String dateStr, String format)
			throws ParseException {
		if (dateStr == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateStr);
	}

	/**
	 * 取得当前年份
	 * 
	 * @return
	 */
	public static String getYearString() {
		DateFormat dateFormat = getNewDateFormat(YEAR_FORMAT);

		return getDateString(new Date(), dateFormat);
	}

	/**
	 * 格式化日期，去除时间
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @return 格式化后的日期
	 * @throws ParseException
	 *             时间格式化异常
	 */
	public static Date parseDateNoTime(String sDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(SHORT_FORMAT);

		if ((sDate == null) || (sDate.length() < SHORT_FORMAT.length())) {
			throw new ParseException("length too little", 0);
		}

		if (!StringUtils.isNumeric(sDate)) {
			throw new ParseException("not all digit", 0);
		}

		return dateFormat.parse(sDate);
	}

	/**
	 * 格式化日期，去除时间
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @param format
	 *            需要转换的格式
	 * @return 格式化后的日期
	 * @throws ParseException
	 *             时间格式化异常
	 */
	public static Date parseDateNoTime(String sDate, String format)
			throws ParseException {
		if (StringUtils.isBlank(format)) {
			throw new ParseException("Null format. ", 0);
		}

		DateFormat dateFormat = new SimpleDateFormat(format);

		if ((sDate == null) || (sDate.length() < format.length())) {
			throw new ParseException("length too little", 0);
		}

		return dateFormat.parse(sDate);
	}

	/**
	 * 格式化日期，去除时间
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @param delimit
	 *            替换字符
	 * @return 格式化后的日期
	 * @throws ParseException
	 *             时间格式化异常
	 */
	public static Date parseDateNoTimeWithDelimit(String sDate, String delimit)
			throws ParseException {
		sDate = sDate.replaceAll(delimit, "");

		DateFormat dateFormat = new SimpleDateFormat(SHORT_FORMAT);

		if ((sDate == null) || (sDate.length() != SHORT_FORMAT.length())) {
			throw new ParseException("length not match", 0);
		}

		return dateFormat.parse(sDate);
	}

	/**
	 * 将时间转换成yyyyMMddHHmmss格式
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @return 格式化后的日期
	 */
	public static Date parseDateLongFormat(String sDate) {
		DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);
		Date d = null;

		if ((sDate != null) && (sDate.length() == LONG_FORMAT.length())) {
			try {
				d = dateFormat.parse(sDate);
			} catch (ParseException ex) {
				return null;
			}
		}

		return d;
	}

	/**
	 * 将时间yyyy-MM-dd HH:mm:ss转换成Date格式
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @return 格式化后的日期
	 */
	public static Date parseDateNewFormat(String sDate) {
		DateFormat dateFormat = new SimpleDateFormat(NEW_FORMAT);
		Date d = null;
		if (sDate != null) {
			try {
				d = dateFormat.parse(sDate);
			} catch (ParseException ex) {
				throw new RuntimeException(
						"将时间转换为yyyy-MM-dd HH:mm:ss格式异常 格式转化前字符串=" + sDate, ex);
			}
		}
		return d;
	}

	/**
	 * 将yyyy-MM-dd转换成时间格式
	 * 
	 * @param sDate
	 *            格式化前的日期
	 * @return 格式化后的日期
	 */
	public static Date parseDateFormat(String sDate) {
		DateFormat dateFormat = new SimpleDateFormat(WEB_FORMAT);
		Date d = null;
		if (StringUtils.isNotBlank(sDate)) {
			try {
				d = dateFormat.parse(sDate);
			} catch (ParseException ex) {
				throw new RuntimeException("将yyyy-MM-dd转换成时间格式, 异常 格式化前字符串="
						+ sDate, ex);
			}
		}
		return d;
	}

	/**
	 * 解析日期字符串以"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"格式到Date
	 * 
	 * @param dateStr
	 *            格式化前的日期
	 * @return 格式化后的日期
	 */
	public static Date parseDate(String dateStr) {
		Date date = null;
		if (dateStr != null) {
			String[] parsePatterns = { WEB_FORMAT, NEW_FORMAT };
			try {
				date = org.apache.commons.lang3.time.DateUtils.parseDate(
						dateStr, parsePatterns);
			} catch (ParseException e) {
				throw new RuntimeException("日期转化异常,格式化前字符串=" + dateStr, e);
			}
		}
		return date;
	}

	/**
	 * 将时间转换为自定义格式
	 * 
	 * @param sDate
	 *            格式化钱的日期
	 * @param format
	 *            时间格式化样式
	 * @return 格式化后的日期
	 */
	public static Date parseDateFormat(String sDate, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date d = null;
		if (StringUtils.isBlank(sDate) || StringUtils.isBlank(format)
				|| sDate.length() != format.length()) {
			return d;
		}
		try {
			d = dateFormat.parse(sDate);
		} catch (ParseException e) {
			throw new RuntimeException("将时间转换为自定义格式异常 格式化前字符串=" + sDate
					+ " ,格式化样式=" + format, e);
		}
		return d;
	}

	/**
	 * 计算当前时间几小时之前的时间
	 *
	 * @param date
	 * @param hours
	 *
	 * @return
	 */
	public static Date decreaseHours(Date date, long hours) {
		return decreaseMinutes(date, hours * 60);
	}

	/**
	 * 计算当前时间几分钟之前的时间
	 *
	 * @param date
	 * @param minutes
	 *
	 * @return
	 */
	public static Date decreaseMinutes(Date date, long minutes) {
		return decreaseSeconds(date, minutes * 60);
	}

	/**
	 * @param date1
	 * @param secs
	 *            计算当前时间几秒钟之前的时间
	 * @return
	 */

	public static Date decreaseSeconds(Date date1, long secs) {
		return new Date(date1.getTime() - (secs * 1000));
	}

	/**
	 * 计算当前时间几小时之后的时间
	 *
	 * @param date
	 *            时间
	 * @param hours
	 *            小时数
	 *
	 * @return 转换后的时间
	 */
	public static Date addHours(Date date, long hours) {
		return addMinutes(date, hours * 60);
	}

	/**
	 * 计算当前时间几分钟之后的时间
	 *
	 * @param date
	 *            时间
	 * @param minutes
	 *            分钟数
	 *
	 * @return 转换后的时间
	 */
	public static Date addMinutes(Date date, long minutes) {
		return addSeconds(date, minutes * 60);
	}

	/**
	 * 增加毫秒数
	 * 
	 * @param date1
	 *            日期
	 * @param secs
	 *            毫秒数
	 * @return 增加后的毫秒数
	 */
	public static Date addSeconds(Date date1, long secs) {
		return new Date(date1.getTime() + (secs * 1000));
	}

	/**
	 * 判断输入的字符串是否为合法的小时
	 *
	 * @param hourStr
	 *            小时
	 *
	 * @return true/false 是否合法
	 */
	public static boolean isValidHour(String hourStr) {
		if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
			int hour = new Integer(hourStr).intValue();

			if ((hour >= 0) && (hour <= 23)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断输入的字符串是否为合法的分或秒
	 *
	 * @param str
	 *            分或秒
	 *
	 * @return true/false 是否合法
	 */
	public static boolean isValidMinuteOrSecond(String str) {
		if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
			int hour = new Integer(str).intValue();

			if ((hour >= 0) && (hour <= 59)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 取得新的日期
	 *
	 * @param date1
	 *            日期
	 * @param days
	 *            天数
	 *
	 * @return 新的日期
	 */
	public static Date addDays(Date date1, long days) {
		return addSeconds(date1, days * ONE_DAY_SECONDS);
	}

	/**
	 * 获取明天的日期
	 * 
	 * @param sDate
	 *            今天的日期
	 * @return 明天的日期
	 * @throws ParseException
	 */
	public static String getTomorrowDateString(String sDate)
			throws ParseException {
		Date aDate = parseDateNoTime(sDate);

		aDate = addSeconds(aDate, ONE_DAY_SECONDS);

		return getDateString(aDate);
	}

	/**
	 * 获得yyyyMMddHHmmss格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getLongDateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(LONG_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 获得yyyyMMddHHmm格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getLowwerLongDateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(LOWER_LONG_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 获得yyyy-MM-dd HH:mm:ss格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getNewFormatDateString(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat dateFormat = new SimpleDateFormat(NEW_FORMAT);
		return getDateString(date, dateFormat);
	}

	/**
	 * 获得HH:mm格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getHoursFormatDateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(HOURS_FORMAT);
		return getDateString(date, dateFormat);
	}

	/**
	 * 获得HH:mm:ss格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getSecondFormatDateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat(SECOND_FORMAT);
		return getDateString(date, dateFormat);
	}

	/**
	 * 格式化时间
	 * 
	 * @param date
	 *            转换前的日期
	 * @param dateFormat
	 *            需要转换的格式
	 * @return 转换后的日期
	 */
	public static String getDateString(Date date, DateFormat dateFormat) {
		if (date == null || dateFormat == null) {
			return null;
		}

		return dateFormat.format(date);
	}

	/**
	 * 获得昨天的时间
	 * 
	 * @param sDate
	 *            今天的时间
	 * @return 昨天的时间
	 * @throws ParseException
	 */
	public static String getYesterDayDateString(String sDate)
			throws ParseException {
		Date aDate = parseDateNoTime(sDate);

		aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

		return getDateString(aDate);
	}

	/**
	 * 当天的时间格式化为 yyyyMMdd
	 * 
	 * @param date
	 *            转换前的日期
	 * @return 转换后的日期
	 */
	public static String getDateString(Date date) {
		DateFormat df = getNewDateFormat(SHORT_FORMAT);

		return df.format(date);
	}

	/**
	 * 获得yyyy-MM-dd格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getWebDateString(Date date) {
		DateFormat dateFormat = getNewDateFormat(WEB_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 取得“X年X月X日”的日期格式
	 *
	 * @param date
	 *            转换前的时间
	 *
	 * @return 转换后的时间
	 */
	public static String getChineseDateString(Date date) {
		DateFormat dateFormat = getNewDateFormat(CHINESE_DT_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 取得“X年X月X日x点x分x秒”的日期格式
	 *
	 * @param date
	 *            转换前的时间
	 *
	 * @return 转换后的时间
	 */
	public static String getChineseAllDateString(Date date) {
		DateFormat dateFormat = getNewDateFormat(CHINESE_ALL_DATE_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 获得yyyyMMdd格式的时间
	 * 
	 * @return 转换后的时间
	 */
	public static String getTodayString() {
		DateFormat dateFormat = getNewDateFormat(SHORT_FORMAT);

		return getDateString(new Date(), dateFormat);
	}

	/**
	 * 获得HHmmss格式的时间
	 * 
	 * @param date
	 *            转换前的时间
	 * @return 转换后的时间
	 */
	public static String getTimeString(Date date) {
		DateFormat dateFormat = getNewDateFormat(TIME_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static long getTime() {
		return new Date().getTime();
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param days
	 *            天数
	 * @return 几天前的时间
	 */
	public static Date getBeforeDay(int days) {
		return new Date(System.currentTimeMillis()
				- (ONE_DAY_MILL_SECONDS * days));
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param days
	 *            天数
	 * @return 几天前的时间 "yyyyMMdd"
	 */
	public static String getBeforeDayString(int days) {
		Date date = new Date(System.currentTimeMillis()
				- (ONE_DAY_MILL_SECONDS * days));
		DateFormat dateFormat = getNewDateFormat(SHORT_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param days
	 *            天数
	 * @return 几天前的时间 "yyyy-MM-dd"
	 */
	public static String getBeforeDayStringWithWebFormat(int days) {
		Date date = new Date(System.currentTimeMillis()
				- (ONE_DAY_MILL_SECONDS * days));
		DateFormat dateFormat = getNewDateFormat(WEB_FORMAT);

		return getDateString(date, dateFormat);
	}

	/**
	 * 根据结束时间和开始时间获取间隔毫秒数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔毫秒数
	 * @throws Exception 
	 */
	public static long getDiffMillis(Date endDate, Date beginDate) throws Exception {
		AssertUtil.notNull(endDate, "结束时间不能为Null.");
		AssertUtil.notNull(beginDate, "开始时间不能为Null.");

		return endDate.getTime() - beginDate.getTime();
	}

	/**
	 * 根据结束时间和开始时间获取间隔秒数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔秒数
	 * @throws Exception 
	 */
	public static long getDiffSeconds(Date endDate, Date beginDate) throws Exception {
		return getDiffMillis(endDate, beginDate) / 1000L;
	}

	/**
	 * 根据结束时间和开始时间获取间隔分钟数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔分钟数
	 * @throws Exception 
	 */
	public static long getDiffMinutes(Date endDate, Date beginDate) throws Exception {
		return getDiffMillis(endDate, beginDate) / (60 * 1000L);
	}

	/**
	 * 根据结束时间和开始时间获取间隔小时数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔小时数
	 * @throws Exception 
	 */
	public static long getDiffHours(Date endDate, Date beginDate) throws Exception {
		return getDiffMillis(endDate, beginDate) / (60 * 60 * 1000L);
	}

	/**
	 * 根据结束时间和开始时间获取间隔天数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔天数
	 * @throws Exception 
	 */
	public static long getDiffDays(Date endDate, Date beginDate) throws Exception {
		return getDiffMillis(endDate, beginDate) / (24 * 60 * 60 * 1000L);
	}

	/**
	 * 根据结束时间和开始时间获取间隔年数
	 *
	 * @param endDate
	 *            结束时间
	 * @param beginDate
	 *            开始时间
	 * @return 间隔年数
	 * @throws Exception 
	 */
	public static long getDiffYears(Date endDate, Date beginDate) throws Exception {
		AssertUtil.notNull(endDate, "结束时间不能为Null.");
		AssertUtil.notNull(beginDate, "开始时间不能为Null.");

		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		int endYear = endCalendar.get(Calendar.YEAR);
		int endMonth = endCalendar.get(Calendar.MONTH);
		int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		int beginYear = beginCalendar.get(Calendar.YEAR);
		int beginMonth = beginCalendar.get(Calendar.MONTH);
		int beginDay = beginCalendar.get(Calendar.DAY_OF_MONTH);

		int years;
		if (beginDate.before(endDate)) {
			if (endMonth > beginMonth
					|| (endMonth == beginMonth && endDay >= beginDay)) {
				years = endYear - beginYear;
			} else {
				years = endYear - beginYear - 1;
			}
		} else {
			if (beginMonth > endMonth
					|| (endMonth == beginMonth && beginDay >= endDay)) {
				years = beginYear - endYear;
			} else {
				years = beginYear - endYear - 1;
			}
			years = 0 - years;
		}
		return years;
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param dateString
	 *            时间
	 * @param days
	 *            天数
	 * @return 转换后的时间
	 */
	public static String getBeforeDayString(String dateString, int days) {
		Date date;
		DateFormat df = getNewDateFormat(SHORT_FORMAT);

		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

		return df.format(date);
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param dateString
	 *            时间
	 * @param days
	 *            天数
	 * @return 转换后的时间 格式为""yyyy-MM-dd""
	 */
	public static String getBeforeDayStringWithWebFormat(String dateString,
			int days) {
		Date date;
		DateFormat df = getNewDateFormat(WEB_FORMAT);

		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

		return df.format(date);
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param dateString
	 *            时间
	 * @param days
	 *            天数
	 * @return 转换后的时间 格式为"yyyy-MM-dd HH:mm:ss"
	 */
	public static String getBeforeDayStringWithNewFormat(String dateString,
			int days) {
		Date date;
		DateFormat df = getNewDateFormat(NEW_FORMAT);

		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

		return df.format(date);
	}

	/**
	 * 获得几天前的时间
	 * 
	 * @param dateString
	 *            时间
	 * @param days
	 *            天数
	 * @return 转换后的时间 格式为""yyyy-MM-dd HH:mm:ss""
	 */
	public static String getBeforeDayStringWithFormat(String dateString,
			int days) {
		Date date;
		DateFormat df = getNewDateFormat(NEW_FORMAT);

		try {
			date = df.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}

		date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

		return df.format(date);
	}

	/**
	 * 验证是否合法的yyyyMMdd时间格式
	 * 
	 * @param strDate
	 *            时间
	 * @return 是否合法
	 */
	public static boolean isValidShortDateFormat(String strDate) {
		if (strDate.length() != SHORT_FORMAT.length()) {
			return false;
		}

		try {
			Integer.parseInt(strDate); // ---- 避免日期中输入非数字 ----
		} catch (Exception NumberFormatException) {
			return false;
		}

		DateFormat df = getNewDateFormat(SHORT_FORMAT);

		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * 验证是否合法的yyyyMMdd时间格式
	 * 
	 * @param strDate
	 *            时间
	 * @param delimiter
	 *            替换字符
	 * @return 是否合法
	 */
	public static boolean isValidShortDateFormat(String strDate,
			String delimiter) {
		String temp = strDate.replaceAll(delimiter, "");

		return isValidShortDateFormat(temp);
	}

	/**
	 * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
	 * 
	 * @param strDate
	 *            时间
	 * @return 转换后的时间
	 */
	public static boolean isValidLongDateFormat(String strDate) {
		if (strDate.length() != LONG_FORMAT.length()) {
			return false;
		}

		try {
			Long.parseLong(strDate); // ---- 避免日期中输入非数字 ----
		} catch (Exception NumberFormatException) {
			return false;
		}

		DateFormat df = getNewDateFormat(LONG_FORMAT);

		try {
			df.parse(strDate);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	/**
	 * 判断表示时间的字符是否为符合yyyyMMddHHmmss格式
	 * 
	 * @param strDate
	 *            时间
	 * @param delimiter
	 *            替换字符
	 * @return 转换后的时间
	 */
	public static boolean isValidLongDateFormat(String strDate, String delimiter) {
		String temp = strDate.replaceAll(delimiter, "");

		return isValidLongDateFormat(temp);
	}

	/**
	 * 获得yyyyMMdd格式的时间
	 * 
	 * @param strDate
	 *            日期
	 * @return 格式化后的时间
	 */
	public static String getShortDateString(String strDate) {
		return getShortDateString(strDate, "-|/");
	}

	/**
	 * 获得yyyyMMdd格式的时间
	 * 
	 * @param strDate
	 *            日期
	 * @param delimiter
	 * @return 格式化后的时间
	 */
	public static String getShortDateString(String strDate, String delimiter) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}

		String temp = strDate.replaceAll(delimiter, "");

		if (isValidShortDateFormat(temp)) {
			return temp;
		}

		return null;
	}

	/**
	 * 获得每个月的第一天
	 * 
	 * @return 获得每个月的第一天
	 */
	public static String getShortFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		Date dt = new Date();

		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		DateFormat df = getNewDateFormat(SHORT_FORMAT);

		return df.format(cal.getTime());
	}

	/**
	 * 获得指定日期当月的第一天
	 * 
	 * @return 获得指定日期当月的第一天
	 */
	public static Date getFirstDayofMonth(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		return cal.getTime();

	}

	/**
	 * 获得指定日期当月的第一天零时
	 * 
	 * @return 获得指定日期当月的第一天零时
	 */
	public static Date getFirstDayofMonthToZero(Date date) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();

	}

	/**
	 * 获得yyyy-MM-dd时间格式
	 * 
	 * @return 获得yyyy-MM-dd时间格式
	 */
	public static String getWebTodayString() {
		DateFormat df = getNewDateFormat(WEB_FORMAT);

		return df.format(new Date());
	}

	/**
	 * 获得每个月的第一天，以yyyy-MM-dd格式
	 * 
	 * @return 获得每个月的第一天
	 */
	public static String getWebFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		Date dt = new Date();

		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);

		DateFormat df = getNewDateFormat(WEB_FORMAT);

		return df.format(cal.getTime());
	}

	/**
	 * 将时间从一种格式转换成另一种格式
	 * 
	 * @param dateString
	 *            时间
	 * @param formatIn
	 *            转换前的格式
	 * @param formatOut
	 *            转换后的格式
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convert(String dateString, DateFormat formatIn,
			DateFormat formatOut) throws ParseException {
		try {
			Date date = formatIn.parse(dateString);

			return formatOut.format(date);
		} catch (ParseException e) {
			throw e;
		}
	}

	/**
	 * 将时间转换成yyyy-MM-dd格式
	 * 
	 * @param dateString
	 *            时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convert2WebFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(SHORT_FORMAT);
		DateFormat df2 = getNewDateFormat(WEB_FORMAT);

		return convert(dateString, df1, df2);
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss时间转换成yyyyMMdd格式
	 * 
	 * @param dateString
	 *            时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convert2ShortFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(NEW_FORMAT);
		DateFormat df2 = getNewDateFormat(SHORT_FORMAT);

		return convert(dateString, df1, df2);
	}

	/**
	 * 将时间转换成yyyy年MM月dd日时间格式
	 * 
	 * @param dateString
	 *            时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convert2ChineseDtFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(SHORT_FORMAT);
		DateFormat df2 = getNewDateFormat(CHINESE_DT_FORMAT);

		return convert(dateString, df1, df2);
	}

	/**
	 * 从yyyy年MM月dd日时间格式转换
	 * 
	 * @param dateString
	 *            转换前的时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convertFromWebFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(SHORT_FORMAT);
		DateFormat df2 = getNewDateFormat(WEB_FORMAT);

		return convert(dateString, df2, df1);
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 是否不小于
	 */
	public static boolean webDateNotLessThan(String date1, String date2) {
		DateFormat df = getNewDateFormat(WEB_FORMAT);

		return dateNotLessThan(date1, date2, df);
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @param format
	 *            yyyy-MM-dd格式的时间
	 *
	 * @return 是否不小于
	 */
	public static boolean dateNotLessThan(String date1, String date2,
			DateFormat format) {
		try {
			Date d1 = format.parse(date1);
			Date d2 = format.parse(date2);

			return d1.after(d2);
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 获取yyyy年MM月dd日HH:mm:ss格式的时间
	 * 
	 * @param today
	 *            今天的时间
	 * @return 转换后的时间
	 */
	public static String getEmailDate(Date today) {
		String todayStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");

		todayStr = sdf.format(today);
		return todayStr;
	}

	/**
	 * 获取MM月dd日HH:mm格式的时间
	 * 
	 * @param today
	 *            今天的时间
	 * @return 转换后的时间
	 */
	public static String getSmsDate(Date today) {
		String todayStr;
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日HH:mm");

		todayStr = sdf.format(today);
		return todayStr;
	}

	/**
	 * 格式化时间段
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @param format
	 *            指定格式
	 * @return 转换后的时间
	 */
	public static String formatTimeRange(Date startDate, Date endDate,
			String format) {
		if ((endDate == null) || (startDate == null)) {
			return null;
		}

		String rt = null;
		long range = endDate.getTime() - startDate.getTime();
		long day = range
				/ org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY;
		long hour = (range % org.apache.commons.lang3.time.DateUtils.MILLIS_PER_DAY)
				/ org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR;
		long minute = (range % org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR)
				/ org.apache.commons.lang3.time.DateUtils.MILLIS_PER_MINUTE;

		if (range < 0) {
			day = 0;
			hour = 0;
			minute = 0;
		}

		rt = format.replaceAll("dd", String.valueOf(day));
		rt = rt.replaceAll("hh", String.valueOf(hour));
		rt = rt.replaceAll("mm", String.valueOf(minute));

		return rt;
	}

	/**
	 * 格式月
	 * 
	 * @param date
	 *            格式化前的时间
	 * @return 格式化后的时间
	 */
	public static String formatMonth(Date date) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(MONTH_FORMAT).format(date);
	}

	/**
	 * 获取系统日期的前一天日期，返回Date
	 *
	 * @return 前一天日期
	 */
	public static Date getBeforeDate() {
		Date date = new Date();

		return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
	}

	/**
	 * 获得指定时间当天起点时间
	 * 
	 * @param date
	 *            当前时间
	 * @return 当天起点时间
	 */
	public static Date getDayBegin(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		df.setLenient(false);

		String dateString = df.format(date);

		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return date;
		}
	}

	/**
	 * 判断参date上min分钟后，是否小于当前时间
	 * 
	 * @param date
	 *            当前时间
	 * @param min
	 *            分钟数
	 * @return 是否小于当前时间
	 */
	public static boolean dateLessThanNowAddMin(Date date, long min) {
		return addMinutes(date, min).before(new Date());

	}

	/**
	 * 是否在当前时间之前
	 * 
	 * @param date
	 *            当前时间
	 * @return 是否在当前时间之前
	 */
	public static boolean isBeforeNow(Date date) {
		if (date == null)
			return false;
		return date.compareTo(new Date()) < 0;
	}

	/**
	 * 转换成yyyy-MM-dd HH:mm时间格式
	 * 
	 * @param sDate
	 *            转换前的时间
	 * @return 转换后的时间
	 * @throws ParseException
	 *             时间格式化问题
	 */
	public static Date parseNoSecondFormat(String sDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(NO_SECOND_FORMAT);

		if ((sDate == null) || (sDate.length() < NO_SECOND_FORMAT.length())) {
			throw new ParseException("length too little", 0);
		}

		return dateFormat.parse(sDate);
	}

	/**
	 * 返回日期的时分
	 * 
	 * @param date
	 *            当前时间
	 * @return 日期的时分
	 */
	public static String getFormatDate(Date date) {
		if (date == null)
			return null;
		String strDate = format(date, DateUtils.NO_SECOND_FORMAT);
		return StringUtils.substring(strDate, 11);
	}

	/**
	 * 获得前一天的时间
	 *
	 * @return
	 */
	public static Date getBeforeFormatDate() throws ParseException {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(c.getTime());
		return formatter.parse(dateStr);
	}

	/**
	 * 获取指定时间的后一天时间
	 *
	 * @param sdate
	 *            指定的时间
	 * @return 指定时间的后一天
	 * @throws ParseException
	 */
	public static Date getAfterFormatDate(Date sdate) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);
		c.add(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = formatter.format(c.getTime());
		return formatter.parse(dateStr);
	}

	/**
	 * 获取上个月当天00:00:00
	 *
	 * @return date
	 */
	public static Date getTodayBeforeThisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (month == 0) {
			year = year - 1;
			month = 11;
		} else {
			month = month - 1;
		}
		calendar.set(year, month, day, 00, 00, 00);
		return calendar.getTime();
	}

	/**
	 * 获取当天23:59：59
	 *
	 * @return Date
	 */
	public static Date getTodayLastSecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期23:59：59
	 *
	 * @return Date
	 */
	public static Date getLastSecond(Date date) {
		if (date == null) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		return calendar.getTime();
	}

	/**
	 * 通过格式化字符串（如"yyyy-MM-dd HH:mm"）获取format对象
	 * 
	 * @param format
	 *            格式化字符串
	 * @return DateFormat format对象
	 */
	public static final DateFormat getFormat(String format) {
		return new SimpleDateFormat(format);
	}

	/**
	 * 转换日期格式字符串
	 *
	 * @param date
	 *            "2010.10.21"
	 * @param time
	 *            "00:00"
	 * @return "2010-10-21 00:00"
	 */
	public static String getFormatString(String date, String time,
			String defaultTime) {
		if (StringUtils.isNotBlank(date)) {
			date = date.replace(".", "-");
			return StringUtils.trim(date)
					+ " "
					+ (StringUtils.isNotBlank(time) ? StringUtils.trim(time)
							: defaultTime);
		}
		return null;
	}

	/**
	 * 字符串 2005-06-30 15:50 转换成时间
	 * 
	 * @param dateString
	 *            String
	 * @return
	 * @throws ParseException
	 */
	public static final Date simpleFormatDate(String dateString)
			throws ParseException {
		if (dateString == null) {
			return null;
		}
		return getFormat(NO_SECOND_FORMAT).parse(dateString);
	}

	/**
	 * 获取HH:SS时间
	 * 
	 * @param endDate
	 *            操作时间
	 * @param type
	 *            时间类型 1:时间往后移, 例如:当前时间为2012-08-30 14:12:00返回时间为2012-08-30
	 *            14:30:00 当前时间为2012-08-30 14:31:00返回时间为2012-08-30 15:00:00
	 *            时间类型2：时间往前移 当前时间为2012-08-30 14:35:00返回时间为2012-08-30 14:30:00
	 *            当前时间为2012-08-30 14:25:00返回时间为2012-08-30 14:00:00
	 * @return 时分
	 */
	public static String gethoursFormatBeforeAndAfter(Date endDate, String type) {

		// 当前小时
		String beforTime = StringUtils.substring(
				DateUtils.getHoursFormatDateString(endDate), 0, 2);
		// 当前分钟
		String endTimeTemp = StringUtils.substring(
				DateUtils.getHoursFormatDateString(endDate), 3, 5);

		int endTime = Integer.parseInt(endTimeTemp);

		// 当type等于1时间往后靠,说明取的是结束时间,其它情况取的是开始时间,开始时间往迁移
		if (StringUtils.equals(type, "1")) {

			// 如果当前分钟小于等于30默认为30,例如当前时间为1:25,默认为1:30
			if (endTime <= 30) {
				endTime = 30;
				return beforTime = beforTime + ":" + endTime;
			}
			// 如果当前分钟大于30默认往前追加一个小时,例如当前时间为1:35,默认为2:00
			Date endDateTemp = DateUtils.addHours(endDate, 1);
			beforTime = StringUtils.substring(
					DateUtils.getHoursFormatDateString(endDateTemp), 0, 2)
					+ ":00";

			return beforTime;
		}

		// 如果当前分钟大于等于30默认为30,例如当前时间为1:35,默认为1:30
		if (endTime >= 30) {
			endTime = 30;
			return beforTime = beforTime + ":" + endTime;
		}

		// 如果当前分钟小于30默认为30,例如当前时间为1:25,默认为1:00
		return beforTime + ":00";

	}

	/**
	 * 在日期上增加数个整月
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 加减天数
	 * @param aDate
	 * @param days
	 * @return
	 */
	public static final Date increaseDate(Date aDate, int days) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(aDate);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 是否不小于
	 */
	public static boolean longFormatNotLessThan(String date1, String date2) {
		DateFormat df = getNewDateFormat(LONG_FORMAT);

		return dateNotLessThan(date1, date2, df);
	}

	/**
	 * 比较两个时间的大小 web格式
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 是否不小于
	 */
	public static boolean longFormatNotLessThan(String date1, String date2,
			String format) {
		DateFormat df = getNewDateFormat(format);

		return dateNotLessThan(date1, date2, df);
	}

	/**
	 * 返回长日期格式（yyyyMMddHHmmss格式）
	 *
	 * @param Date
	 * @return
	 */
	public static final String longDate(Date Date) {
		if (Date == null) {
			return null;
		}

		return getFormat(LONG_FORMAT).format(Date);
	}

	/**
	 * 获取日期是星期几
	 * 
	 * @param dt
	 * @return
	 */
	public static int getWeekOfDate(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return w;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date1
	 *            时间1
	 * @param date2
	 *            时间2
	 * @return 是否不小于
	 */
	public static boolean newFormatNotLessThan(String date1, String date2) {
		DateFormat df = getNewDateFormat(NEW_FORMAT);

		return dateNotLessThan(date1, date2, df);
	}

	/**
	 * 将yyyy-MM-dd时间转换成yyyyMMdd格式
	 * 
	 * @param dateString
	 *            时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convertWebFormat2ShortFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(WEB_FORMAT);
		DateFormat df2 = getNewDateFormat(SHORT_FORMAT);

		return convert(dateString, df1, df2);
	}

	/**
	 * 从开始日期，到新增天数期间内，非工作日统计
	 * 
	 * @param startDate
	 *            开始日期
	 * @param addDay
	 *            增加天数
	 * @return 非工作日天数
	 */
	public static int countWeek(Date startDate, int addDay) {
		int countWeek = 0;
		for (int i = 1; i <= addDay; i++) {
			int week = DateUtils.getWeekOfDate(DateUtils.increaseDate(
					startDate, +i));
			if (week == 0 || week == 6) {
				countWeek++;
			}
		}
		return countWeek;
	}

	/**
	 * 
	 * 根据星期获取，星期对应的日期； 如果srcWeek日期超过当前日期,将获取下一周对应星期的日期
	 * 
	 * @param date
	 *            起始日期
	 * @param srcWeek
	 *            星期几
	 * @return
	 */
	public static Date getDayByWeek(Date date, int srcWeek) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int currWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (srcWeek - currWeek >= 0) {
			cal.add(Calendar.DAY_OF_YEAR, srcWeek - currWeek);
		} else {

			cal.add(Calendar.DAY_OF_YEAR, 7 + srcWeek - currWeek);
		}
		return cal.getTime();
	}

	/**
	 * 根据开始日期和需要的工作日天数获取加上指定天数工作日后的日期
	 * 
	 * @param startDate
	 *            开始日期
	 * @param workDay
	 *            工作日天数
	 * @return 指定天数工作日后的日期
	 */
	public static Date getWorkDay(Date startDate, int workDay) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		for (int i = 0; i < workDay; i++) {
			// 判断当天是否为周末,如果是周末加1
			if (Calendar.SATURDAY == c.get(Calendar.SATURDAY)
					|| Calendar.SUNDAY == c.get(Calendar.SUNDAY)) {
				workDay = workDay + 1;
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				continue;
			}
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
			// 当天数加1,判断是否为周末,如果是周末加1
			if (Calendar.SATURDAY == c.get(Calendar.SATURDAY)
					|| Calendar.SUNDAY == c.get(Calendar.SUNDAY)) {
				workDay = workDay + 1;
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				continue;
			}
		}
		return c.getTime();
	}

	/**
	 * 
	 * 除去周末，计算日期
	 * 
	 * @param sDate
	 *            开始时间
	 * @param n
	 *            往后推天数
	 * @return 后推add天数日期
	 */
	public static Date getAfterDate(Date sDate, int n) {
		Date t = sDate;
		for (int i = 1; i <= n;) {
			t = DateUtils.increaseDate(t, +1);
			int week = DateUtils.getWeekOfDate(t);
			if (week != 6 && week != 0) {
				i++;
			}
		}
		return t;
	}

	/**
	 * 把long时间转换为字符串
	 * 
	 * @param millSec
	 * @return
	 */
	public static String transferLongToDate(long millSec) {
		SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
		Date date = new Date(millSec);
		return sdf.format(date);
	}

	/**
	 * 获取上个月的第一天的时间
	 * 
	 * @return
	 */
	public static Date getLastMonthBeginDay() {
		Calendar cal = Calendar.getInstance();
		Date dt = new Date();
		cal.setTime(dt);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 将yyyy-MM-dd HH:mm:ss时间转换成yyyy-MM-dd格式
	 * 
	 * @param dateString
	 *            时间
	 * @return 转换后的时间
	 * @throws ParseException
	 */
	public static String convertNew2WebFormat(String dateString)
			throws ParseException {
		DateFormat df1 = getNewDateFormat(NEW_FORMAT);
		DateFormat df2 = getNewDateFormat(WEB_FORMAT);

		return convert(dateString, df1, df2);
	}
}
