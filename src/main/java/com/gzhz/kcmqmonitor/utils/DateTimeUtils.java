package com.gzhz.kcmqmonitor.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;

public class DateTimeUtils {
    public final static String DATE_FORMAT = "yyyy-MM-dd";
    public final static String DATE_FORMAT_CN = "yyyy年MM月dd日";
    public final static String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm:ss";
    public final static String MONTH_FORMAT = "yyyy-MM";
    public final static String MONTH_FORMAT_MM = "yyyyMM";
    public final static String YEAR_FORMAT_YYYY = "yyyy";
    public final static String DATE_FORMAT_SHORT = "yyyyMMdd";

    public final static String DATE_FORMAT_OTHER = "YYYY-MM-DD'T'hh:mm:ss'Z' ";

    public static float compHour(java.sql.Timestamp date1, java.sql.Timestamp date2) {
        return (float) ((float) (date1.getTime() - date2.getTime()) / 3600 / 1000.0);

    }

    public static int dayOfYear(java.sql.Timestamp saletime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(saletime.getTime()));
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 比较日期的相差天数
     *
     * @param begindate
     * @param enddate
     * @return
     */
    public static int compDateOfDay(java.util.Date begindate, java.util.Date enddate) {
        return (int) ((begindate.getTime() - enddate.getTime()) / 3600 / 24 / 1000);

    }

    /**
     * getCurrentLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获取当前时间字符串
     * @Exception 异常对象
     */
    public static String getCurrentLongDate() throws Exception {
        return DateTimeUtils.dateToLongStr(new java.util.Date());
    }

    public static String getCurrentDate() throws Exception {
        return DateTimeUtils.dateToStr(new java.util.Date());
    }

    /**
     * getSqlLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获取SQL当前时间
     * @Exception 异常对象
     */
    public static java.sql.Timestamp getSqlLongDate() throws Exception {
        return new java.sql.Timestamp(new java.util.Date().getTime());
    }

    /**
     * getSqlLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获取SQL当前时间
     * @Exception 异常对象
     */
    public static java.sql.Timestamp getSqlLongDate(long time) throws Exception {
        return new java.sql.Timestamp(time);
    }

    /**
     * getSqlDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获取jdbc时间的当前时间
     * @Exception 异常对象
     */
    public static java.sql.Date getSqlDate() throws Exception {
        return new java.sql.Date(new java.util.Date().getTime());
    }

    /**
     * getCurrentLongDate_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获取当前时间字符串
     * @Exception 异常对象
     */
    public static String getCurrentLongDate_CN() throws Exception {
        return DateTimeUtils.dateToLongStr_CN(new java.util.Date());
    }

    /**
     * strToSqlDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将界面输入的字符串日期， 转换成数据库的日期型
     * @Exception 异常对象
     */
    public static java.sql.Date strToSqlDate(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }


    /**
     * strToSqlDateTime
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将界面输入的字符串日期， 转换成数据库的日期型
     * @Exception 异常对象
     */
    public static java.sql.Date strToSqlDateTime(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }


    /**
     * strToSqlDate_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将界面输入的字符串日期， 转换成数据库的日期型
     * @Exception 异常对象
     */
    public static java.sql.Date strToSqlDate_CN(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_CN);
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    public static String strToSqlstr_CN(String date) throws Exception {
        return date.substring(0, 4) + "年" + date.substring(5, 7) + "月" + date.substring(8, 10) + "日";
    }

    /**
     * strToDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：从字符串转换到java的时间对象
     * @Exception 异常对象
     */
    public static java.util.Date strToDate(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return new java.util.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }


    /**
     * strToDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：从字符串转换到java的时间对象
     * @Exception 异常对象
     */
    public static java.util.Date strToDate(String date, String format) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return new java.util.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }


    public static String strToDateshortStr(java.util.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static String strToDateshort() throws Exception {
        return DateTimeUtils.strToDateshortStr(new java.util.Date());
    }

    public static java.util.Date strToDateshort(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_SHORT);
            return new java.util.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * strToDate_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：从字符串转换到java的时间对象
     * @Exception 异常对象
     */
    public static java.util.Date strToDate_CN(String date) throws Exception {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_CN);
            return new java.util.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * sqlDateToStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将日期转换成短日期字符串
     * @Exception 异常对象
     */
    public static String sqlDateToStr(java.sql.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }

    }

    public static String sqlDateToStr(java.sql.Timestamp date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }

    }

    /**
     * sqlDateToStr_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将日期转换成短日期字符串
     * @Exception 异常对象
     */
    public static String sqlDateToStr_CN(java.sql.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_CN);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * dateToStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将java时间转换成字符串
     * @Exception 异常对象
     */
    public static String dateToStr(java.util.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }

    }


    /**
     * @param date
     * @param format
     * @return
     * @throws Exception
     */

    public static String dateToStr(java.util.Date date, String format) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }

    }


    /**
     * dateToStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将java时间转换成字符串
     * @Exception 异常对象
     */
    public static String monthDateToStr(java.util.Date date) throws Exception {
        try {
            if (date == null) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(MONTH_FORMAT);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }

    }


    /**
     * dateToStr_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将java时间转换成字符串
     * @Exception 异常对象
     */
    public static String dateToStr_CN(java.util.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_CN);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }

    }

    /**
     * sqlDateToLongStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将jdbc的日期实践性转换成字符串
     * @Exception 异常对象
     */
    public static String sqlDateToLongStr(java.sql.Timestamp date)
            throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }

    }

    /**
     * sqlDateToLongStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将jdbc的日期实践性转换成字符串
     * @Exception 异常对象
     */
    public static String sqlDateToLongStr(java.sql.Timestamp date, String formmat)
            throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(formmat);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }

    }

    /**
     * sqlDateToLongStr_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将jdbc的日期实践性转换成字符串
     * @Exception 异常对象
     */
    public static String sqlDateToLongStr_CN(java.sql.Timestamp date)
            throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_CN);
            return sdf.format(new java.util.Date(date.getTime()));
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * dateToLongStr
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将java的日期格式化成字符串
     * @Exception 异常对象
     */
    public static String dateToLongStr(java.util.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * dateToLongStr_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将java的日期格式化成字符串
     * @Exception 异常对象
     */
    public static String dateToLongStr_CN(java.util.Date date) throws Exception {
        try {
            if (date == null)
                return "";
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_CN);
            return sdf.format(date);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    /**
     * strToSqlLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：转换为jdbc日期时间型
     * @Exception 异常对象
     */
    public static java.sql.Timestamp strToSqlLongDate(String date)
            throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return new java.sql.Timestamp(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * strToSqlLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：转换为jdbc日期时间型
     * @Exception 异常对象
     */
    public static java.sql.Timestamp strToSqlLongStamp(String date)
            throws Exception {
        try {
            SimpleDateFormat sdf = null;
            if (date.length() == 10) {
                sdf = new SimpleDateFormat(DATE_FORMAT);
            } else {
                sdf = new SimpleDateFormat(TIME_FORMAT);
            }
            return new java.sql.Timestamp(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    public static java.sql.Date strToSqlLongDatepd(String date)
            throws Exception {
        try {
            SimpleDateFormat sdf = null;
            if (date.length() == 10) {
                sdf = new SimpleDateFormat(DATE_FORMAT);
            } else {
                sdf = new SimpleDateFormat(TIME_FORMAT);
            }
            return new java.sql.Date(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * strToSqlLongDate_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：转换为jdbc日期时间型
     * @Exception 异常对象
     */
    public static java.sql.Timestamp strToSqlLongDate_CN(String date)
            throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_CN);
            return new java.sql.Timestamp(sdf.parse(date).getTime());
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * strToLongDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将字符串转换成java的日期类型
     * @Exception 异常对象
     */
    public static java.util.Date strToLongDate(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
            return sdf.parse(date);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * strToLongDate_CN
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：将字符串转换成java的日期类型
     * @Exception 异常对象
     */
    public static java.util.Date strToLongDate_CN(String date) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_CN);
            return sdf.parse(date);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * getNextDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：日期的下一天
     * @Exception 异常对象
     */
    public static java.util.Date getNextDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + 1);
        return calendar.getTime();
    }


    /**
     * incDay
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：加天数
     * @Exception 异常对象
     */
    public static java.util.Date incDay(java.util.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + days);
        return calendar.getTime();

    }

    public static java.util.Date incHour(java.util.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.HOUR, day + days);
        return calendar.getTime();

    }

    /**
     * incMonth
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：加月份
     * @Exception 异常对象
     */

    public static java.util.Date incMonth(java.util.Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, month + months);

        return calendar.getTime();

    }

    /**
     * incYear
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：添加年
     * @Exception 异常对象
     */
    public static java.util.Date incYear(java.util.Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, year + years);
        return calendar.getTime();

    }

    /**
     * incMinute
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：添加分钟
     * @Exception 异常对象
     */
    public static java.util.Date incMinute(java.util.Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        calendar.set(Calendar.MINUTE, minute + minutes);
        return calendar.getTime();

    }

    /**
     * getPreviousDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获得某一日期的前一天
     * @Exception 异常对象
     */
    public static java.util.Date getPreviousDate(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day - 1);
        return calendar.getTime();
    }

    /**
     * getFirstDayOfMonth
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获得某年某月第一天的日期
     * @Exception 异常对象
     */
    public static java.util.Date getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * getLastDayOfMonth
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获得某年某月最后一天的日期
     * @Exception 异常对象
     */
    public static java.util.Date getLastDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        return getPreviousDate(calendar.getTime());
    }

    /**
     * buildDate
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：由年月日构建
     * @Exception 异常对象
     */
    public static java.util.Date buildDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * getDayCountOfMonth
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：取得某月的天数
     * @Exception 异常对象
     */
    public static int getDayCountOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 0);
        return calendar.get(Calendar.DATE);
    }

    /**
     * ]
     * <p>
     * getLastDayOfQuarter
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述： 获得某年某季度的最后一天的日期
     * @Exception 异常对象
     */
    public static java.util.Date getLastDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = quarter * 3;
        }
        return getLastDayOfMonth(year, month);

    }

    /**
     * getFirstDayOfQuarter
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：获得某年某季度的第一天的日期
     * @Exception 异常对象
     */
    public static java.util.Date getFirstDayOfQuarter(int year, int quarter) {
        int month = 0;
        if (quarter > 4) {
            return null;
        } else {
            month = (quarter - 1) * 3 + 1;
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 获得某年的第一天的日期
     *
     * @param year
     * @return Date
     */
    public static java.util.Date getFirstDayOfYear(int year) {
        return getFirstDayOfMonth(year, 1);
    }

    /**
     * 获得某年的最后一天的日期
     *
     * @param year
     * @return Date
     */
    public static java.util.Date getLastDayOfYear(int year) {
        return getLastDayOfMonth(year, 12);
    }

    /**
     * 将不足两位的月份或日期补足为两位
     *
     * @param decimal
     * @return
     */
    public static String formatMonthDay(int decimal) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(decimal);
    }

    /**
     * 比较日期，相差整数部分是天 返回值小于0 date1早于date2 返回值大于0 date1晚于date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static float compDate(Date date1, Date date2) {
        return (float) ((float) (date1.getTime() - date2.getTime()) / 3600 / 24 / 1000.0);

    }

    /**
     * 比较日期的相差天数
     *
     * @param enddate
     * @param begindate
     * @return
     */
    public static int compDateOfDay(Date enddate, Date begindate) {
        return (int) ((enddate.getTime() - begindate.getTime()) / 3600 / 24 / 1000);

    }

    public static int compDateOfDay1(java.util.Date enddate, java.util.Date begindate) {
        return (int) ((enddate.getTime() - begindate.getTime()) / 3600 / 24 / 1000);

    }

    /**
     * dateDiff
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：计算日期相差天数
     * @Exception 异常对象
     */
    public int dateDiff(Object beginDate, Object endDate, String flag) {
        if (!StringUtils.isNotBlank(flag)) {
            return -1;
        } else {
            if ((beginDate == null) || (endDate == null)) {
                return 0;
            }
            java.util.Date beginTime = null;
            java.util.Date endTime = null;
            if (beginDate instanceof java.util.Date) {
                beginTime = (java.util.Date) beginDate;
            } else if (beginDate instanceof java.sql.Date) {
                java.sql.Date sqldate = (java.sql.Date) beginDate;
                beginTime = new java.util.Date(sqldate.getTime());
            } else if (beginDate instanceof java.sql.Timestamp) {
                java.sql.Timestamp sqltime = (java.sql.Timestamp) beginDate;
                beginTime = new java.util.Date(sqltime.getTime());
            }
            if (endDate instanceof java.util.Date) {
                endTime = (java.util.Date) endDate;
            } else if (endDate instanceof java.sql.Date) {
                java.sql.Date sqldate = (java.sql.Date) endDate;
                endTime = new java.util.Date(sqldate.getTime());
            } else if (endDate instanceof java.sql.Timestamp) {
                java.sql.Timestamp sqltime = (java.sql.Timestamp) endDate;
                endTime = new java.util.Date(sqltime.getTime());
            }
            if (flag.equalsIgnoreCase("year")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(beginTime);
                int beginYear = calendar.get(Calendar.YEAR);
                calendar.setTime(endTime);
                int endYear = calendar.get(Calendar.YEAR);
                return beginYear - endYear;
            } else if (flag.equalsIgnoreCase("month")) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(beginTime);
                int beginYear = calendar.get(Calendar.YEAR);
                int beginMonth = calendar.get(Calendar.MONTH);
                calendar.setTime(endTime);
                int endYear = calendar.get(Calendar.YEAR);
                int endMonth = calendar.get(Calendar.MONTH);
                return (beginYear * 12 + beginMonth)
                        - (endYear * 12 + endMonth);
            } else if (flag.equalsIgnoreCase("day")) {
                return (int) ((beginTime.getTime() - endTime.getTime()) / 3600 / 24 / 1000);
            } else if (flag.equalsIgnoreCase("hour")) {
                return (int) ((beginTime.getTime() - endTime.getTime()) / 3600 / 1000);
            } else if (flag.equalsIgnoreCase("minute")) {
                return (int) ((beginTime.getTime() - endTime.getTime()) / 60 / 1000);
            } else if (flag.equalsIgnoreCase("second")) {
                return (int) ((beginTime.getTime() - endTime.getTime()) / 1000);
            } else if (flag.equalsIgnoreCase("mill")) {
                return (int) (beginTime.getTime() - endTime.getTime());
            }
        }
        return 0;
    }

    /**
     * dateAdd
     *
     * @param @return 设定文件
     * @return String DOM对象
     * @描述：日期加
     * @Exception 异常对象
     */
    public java.util.Date dateAdd(Object date, int incNumber, String flag) {
        if (date == null) {
            return null;
        }
        java.util.Date endTime = null;
        if (date instanceof java.util.Date) {
            endTime = (java.util.Date) date;
        } else if (date instanceof java.sql.Date) {
            java.sql.Date sqldate = (java.sql.Date) date;
            endTime = new java.util.Date(sqldate.getTime());
        } else if (date instanceof java.sql.Timestamp) {
            java.sql.Timestamp sqltime = (java.sql.Timestamp) date;
            endTime = new java.util.Date(sqltime.getTime());
        }
        if (flag.equalsIgnoreCase("year")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            int year = calendar.get(Calendar.YEAR);
            calendar.set(Calendar.YEAR, year + incNumber);
            return calendar.getTime();
        } else if (flag.equalsIgnoreCase("month")) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTime);
            int month = calendar.get(Calendar.MONTH);
            calendar.set(Calendar.MONTH, month + incNumber);
            return calendar.getTime();
        } else if (flag.equalsIgnoreCase("day")) {
            return new java.util.Date(endTime.getTime() + 3600 * 1000 * 24
                    * incNumber);
        } else if (flag.equalsIgnoreCase("hour")) {
            return new java.util.Date(endTime.getTime() + 3600 * 1000
                    * incNumber);
        } else if (flag.equalsIgnoreCase("minute")) {
            return new java.util.Date(endTime.getTime() + 60 * 1000 * incNumber);
        } else if (flag.equalsIgnoreCase("second")) {
            return new java.util.Date(endTime.getTime() + 1000 * incNumber);
        } else if (flag.equalsIgnoreCase("mill")) {
            return new java.util.Date(endTime.getTime() + incNumber);
        }
        return null;
    }

    /**
     * 将Sql Time 转换为字符串
     *
     * @param time
     * @return
     */
    public static String sqlTimeToStr(Time time) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "HH:mm:ss");
        java.util.Date date = new java.util.Date(time.getTime());
        return sdf.format(date);

    }

    /**
     * 根据指定日期获取所在周的周一
     *
     * @param date
     * @return
     */
    public static java.util.Date getWeekMonday(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }

        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);//根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return cal.getTime();
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static java.util.Date getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        return cal.getTime();
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static java.util.Date getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        return cal.getTime();
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(java.util.Date nowTime, java.util.Date beginTime,
                                         java.util.Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        begin.add(Calendar.DATE, -1);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static java.util.Date getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static java.util.Date getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 获取本年的第一天
     *
     * @return String
     **/
    public static java.util.Date getYearStart() throws ParseException {
        String dateStr = new SimpleDateFormat("yyyy").format(new java.util.Date()) + "-01-01";
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
    }

    /**
     * 获取本年的最后一天
     *
     * @return String
     **/
    public static java.util.Date getYearEnd() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        java.util.Date currYearLast = calendar.getTime();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(currYearLast) + " 23:59:59";
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
    }

    /**
     * 根据数字转换为星期几
     *
     * @param week
     * @return
     */
    public static String numberToChineseWeek(Integer week) {
        String res = "";
        switch (week) {
            case 0:
                res = "星期一";
                break;
            case 1:
                res = "星期二";
                break;
            case 2:
                res = "星期三";
                break;
            case 3:
                res = "星期四";
                break;
            case 4:
                res = "星期五";
                break;
            case 5:
                res = "星期六";
                break;
            case 6:
                res = "星期日";
                break;
            default:
                res = "";
                break;
        }
        return res;
    }

    /**
     * 获取指定开始以及结束日期内的天数集合
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getEndDay(String beginDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dBegin = sdf.parse(beginDate);
        java.util.Date dEnd = sdf.parse(endDate);
        List<String> datas = findDates(dBegin, dEnd);
        return datas;
    }

    public static List<String> findDates(java.util.Date dBegin, java.util.Date dEnd) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        lDate.add(sd.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(sd.format(calBegin.getTime()));
        }
        return lDate;
    }

    /**
     * 日期装换为星期
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat(DateTimeUtils.DATE_FORMAT);
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        java.util.Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 把long 转换成 日期 再转换成String类型
     */
    public static String longToDate(Long millSec, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 获取指定日期所在月份
     *
     * @param date
     * @return
     */
    public static int getTheMonth(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.get(Calendar.MONTH);
    }

    /**
     * Long类型日期装换为星期几
     *
     * @param datetime
     * @return
     */
    public static String longDateToWeek(Long datetime) {
        String dateStr = DateTimeUtils.longToDate(datetime, DateTimeUtils.TIME_FORMAT);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        java.util.Date date;
        try {
            date = f.parse(dateStr);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取一个日期所在的整周日期
     *
     * @param time
     */
    public static List<String> getWeekByDate(java.util.Date time) {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置时间格式
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了

        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        String imptime1 = sdf.format(cal.getTime());

        cal.add(Calendar.DATE, 1);
        String imptime2 = sdf.format(cal.getTime()); //2
        cal.add(Calendar.DATE, 1);
        String imptime3 = sdf.format(cal.getTime()); //3

        cal.add(Calendar.DATE, 1); //4
        String imptime4 = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1); //5
        String imptime5 = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1); //6
        String imptime6 = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 1); //7
        String imptime7 = sdf.format(cal.getTime());


        list.add(imptime1);
        list.add(imptime2);
        list.add(imptime3);
        list.add(imptime4);
        list.add(imptime5);
        list.add(imptime6);
        list.add(imptime7);
        return list;
    }

    /**
         * 时间戳转换成日期格式字符串
          * @param seconds 精确到秒的字符串  时间戳是10位的长度
           */
     public static String timeStamp2Date(String seconds,String format) {
                if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
                        return "";
                }
                if(format == null || format.isEmpty()){
                         format = "yyyy-MM-dd HH:mm:ss";
                }
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(new Date(Long.valueOf(seconds+"000")));
            }
}
