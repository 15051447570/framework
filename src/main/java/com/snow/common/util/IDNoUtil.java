
package com.snow.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 身份证号工具类
 * 
 * @author zhouhui
 * @version $Id: IDNoUtil.java, v 0.1 2015年3月27日 下午6:54:04 zhouhui Exp $
 */
public class IDNoUtil {
    /** logger */
    /** 15位身份证号模式 */
    private final static Pattern idCardNumberPattern15 = Pattern.compile("^([0-9]){15}$");
    /** 18位身份证号模式 */
    private final static Pattern idCardNumberPattern18 = Pattern.compile("^([0-9]){17}[0-9Xx]{1}$");

    /**
     * 将身份证号规格化为18位。
     * 
     * @param idCardNumber 15或18位的身份证号
     * @return 18位规格化的身份证号，可能返回<code>null</code>
     * @throws Exception 
     */
    public static String getIdCardNumber18(String idCardNumber) throws Exception {
        if (StringUtils.isEmpty(idCardNumber)) {
            return null;
        }
        //18位身份证最后一位应该是X
        if (idCardNumber.length() == 18) {
            return idCardNumber.toUpperCase();
        } else if (idCardNumber.length() != 15) {
            return null;
        }
        // 首先将身份证号码扩展至17位: 将出生年扩展为19XX的形式 
        String idCardNumber17 = idCardNumber.substring(0, 6) + "19" + idCardNumber.substring(6);

        //计算校验码 
        int nSum = 0;
        try {
            for (int nCount = 0; nCount < 17; nCount++) {
                nSum += (Integer.parseInt(idCardNumber17.substring(nCount, nCount + 1)) * (Math
                    .pow(2, 17 - nCount) % 11));
            }
        } catch (Exception e) {
            throw new Exception("将身份证号规格化为18位时出现异常：", e);
        }

        nSum %= 11;

        if (nSum <= 1) {
            nSum = 1 - nSum;
        } else {
            nSum = 12 - nSum;
        }
        //18位身份证最后一位应该是X
        if (nSum == 10) {
            return idCardNumber17 + "X";
        } else {
            return idCardNumber17 += nSum;
        }
    }

    /**
     * 检验身份证号码长度格式
     * 
     * @param idCardNumber 15或18位的身份证号
     * @return true: 有效  false:无效
     */
    public static boolean checkCertNoLength(String idCardNumber) {
        if (StringUtils.isEmpty(idCardNumber)) {
            return false;
        }
        if (idCardNumber.length() == 15 || idCardNumber.length() == 18) {
            return true;
        }
        return false;
    }

    /**
     * 将身份证号规格化为15位。
     * 
     * @param idCardNumber 15或18位的身份证号
     * @return 15位规格化的身份证号，可能返回<code>null</code>
     */
    public static String getIdCardNumber15(String idCardNumber) {
        if (StringUtils.isEmpty(idCardNumber)) {
            return null;
        }
        if (idCardNumber.length() == 15) {
            return idCardNumber;
        } else if (idCardNumber.length() == 18) {
            return idCardNumber.substring(0, 6) + idCardNumber.substring(8, 17);
        }
        return null;
    }

    /**
     * 判断两个身份证号码是否一致，可以屏蔽15＆18区分，直接可以判断身份证是否一致。
     *
     * @param newCertNo 身份证号1
     * @param oldCertNo 身份证号2
     * @return 判断结果
     * @throws Exception 
     */
    public static boolean checkCertNoEquals(String newCertNo, String oldCertNo) throws Exception {
        if (StringUtils.isBlank(oldCertNo) || StringUtils.isBlank(newCertNo)) {
            return false;
        }

        if (!checkIdCardNumber(newCertNo) || !checkIdCardNumber(oldCertNo)) {
            return false;
        }

        if (StringUtils.equalsIgnoreCase(oldCertNo, getIdCardNumber15(newCertNo))
            || StringUtils.equalsIgnoreCase(oldCertNo, getIdCardNumber18(newCertNo))) {
            return true;
        }

        return false;
    }

    /**
     * 从身份证号析取年龄。
     * 
     * @param idCardNumber 15或18位的身份证号
     * @return 在身份证中析取出的年龄
     * @throws Exception 
     */
    public static int getAgeFromIdCardNumber(String idCardNumber) throws Exception {
        if (StringUtils.isEmpty(idCardNumber)) {
            return 0;
        }
        String strYear = null;

        if (idCardNumber.length() == 15) {
            strYear = "19" + idCardNumber.substring(6, 8);
        } else if (idCardNumber.length() == 18) {
            strYear = idCardNumber.substring(6, 10);
        } else {
            return 0;
        }

        int year = 0;

        try {
            year = Integer.parseInt(strYear);
        } catch (Exception e) {
            throw new Exception("从身份证号析取年龄时出现异常，idCardNumber=" + idCardNumber + "：", e);
        }

        int thisYear = (new GregorianCalendar()).get(Calendar.YEAR);

        return (thisYear - year);
    }

    /**
     * 从身份证号判断是否成年(>18周岁)。
     * 
     * @param idCardNumber 15或18位的身份证号
     * @return 判断结果
     */
    public static boolean checkAgeIsAdult(String idCardNumber) {
        if (StringUtils.isEmpty(idCardNumber)) {
            return false;
        }

        String birthYear = null;
        String birthDateAnMouth = null;

        if (idCardNumber.length() == 15) {
            birthYear = "19" + idCardNumber.substring(6, 8);
            birthDateAnMouth = idCardNumber.substring(8, 12);
        } else if (idCardNumber.length() == 18) {
            birthYear = idCardNumber.substring(6, 10);
            birthDateAnMouth = idCardNumber.substring(10, 14);
        } else {
            return false;
        }

        birthYear = Integer.parseInt(birthYear) + 18 + "";

        String adultDay = birthYear + birthDateAnMouth;

        String today = DateUtils.getTodayString();

        boolean a = !(Integer.parseInt(adultDay) > Integer.parseInt(today));

        return a;
    }

    /**
     * 检查身份证号是否有效。
     * 
     * @param idCardNumber 身份证号
     * @return  检查结果
     * @throws Exception 
     */
    public static boolean checkIdCardNumber(String idCardNumber) throws Exception {
        return checkIdCardNumber(idCardNumber, -1);
    }

    /**
     * 根据身份证号码判断是不是男性，在这个方法之前一点要确定传入的号码一定是身份证号码。
     * 
     * @param idCardNumber 合法的身份证号
     * @return 判断结果
     */
    public static boolean checkIsGenderManByIdCardNumber(String idCardNumber) {
        int n = 0;
        if (StringUtils.isBlank(idCardNumber)) {
            return false;
        }
        if (idCardNumber.length() == 18) {
            n = Integer.parseInt(StringUtils.substring(idCardNumber, 16, 17));
        } else {
            n = Integer.parseInt(StringUtils.substring(idCardNumber, 14, 15));
        }

        return n % 2 == 1;
    }

    /**
     * 检查身份证号是否有效。
     * 
     * @param idCardNumber 身份证号
     * @param age 年龄
     * @return 检查结果
     * @throws Exception 
     */
    public static boolean checkIdCardNumber(String idCardNumber, int age) throws Exception {
        if (StringUtils.isBlank(idCardNumber)) {
            return false;
        }
        Matcher matcher = null;

        matcher = idCardNumberPattern15.matcher(idCardNumber);

        if (!matcher.find()) {
            matcher = idCardNumberPattern18.matcher(idCardNumber);

            if (!matcher.find()) {
                return false;
            }
        }

        String idCardNumber18 = getIdCardNumber18(idCardNumber);

        if (idCardNumber18 == null) {
            return false;
        }

        try {
            int year = Integer.parseInt(idCardNumber18.substring(6, 10));
            int month = Integer.parseInt(idCardNumber18.substring(10, 12));
            int day = Integer.parseInt(idCardNumber18.substring(12, 14));

            if (!checkDate(year, month, day)) {
                return false;
            }

            if (age > (getCurrentYear() - year)) {
                return false;
            }
        } catch (Exception e) {
           throw new Exception(e);
        }

        return true;
    }

    /**
     * 检查日期是否有效。
     * 
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 检查结果
     */
    private static boolean checkDate(int year, int month, int day) {
        if ((year < 1900) || (year > getCurrentYear())) {
            return false;
        }

        if ((month < 1) || (month > 12)) {
            return false;
        }

        Calendar cal = new GregorianCalendar();

        cal.set(year, month - 1, 1);

        if ((day < 1) || (day > cal.getActualMaximum(Calendar.DAY_OF_MONTH))) {
            return false;
        }

        return true;
    }

    /**
     * 取得当前年份。
     * 
     * @return 当前年份。
     */
    private static int getCurrentYear() {
        Calendar cal = new GregorianCalendar();

        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }
}
