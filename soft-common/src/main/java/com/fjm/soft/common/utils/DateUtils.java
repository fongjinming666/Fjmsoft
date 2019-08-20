package com.fjm.soft.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.fjm.soft.common.utils.NumberUtils.toInt;
import static java.lang.System.currentTimeMillis;
import static java.util.Calendar.*;
import static java.util.regex.Pattern.compile;

/**
 * 日期相关工具函数.
 *
 * @Author: fongjinming
 * @CreateTime: 2019-08-19 19:17
 * @Description:
 */
public final class DateUtils {

    /*
     * 在单个线程中保持日期对象（以后再实现）.
     *
     * private static final ThreadLocal<Date> DATEHOLDER = new ThreadLocal<Date>();
     */

    /**
     * 格式: yyyy-MM-dd HH:mm:ss.
     **/
    private static final String PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式: yyyy-MM-dd.
     **/
    private static final String PATTERN_DATE = "yyyy-MM-dd";

    /**
     * 格式: yyyy-MM.
     **/
    private static final String PATTERN_MONTH = "yyyy-MM";
    /**
     * 格式: HH:mm:ss.
     **/
    private static final String PATTERN_TIME = "HH:mm:ss";
    /**
     * 格式: yyyy-MM-dd HH:mm:ss.SSS.
     **/
    private static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 1年12月.
     */
    private static final long ONE_YEAR = 12;
    /**
     * 一星期7天.
     */
    private static final int ONE_WEEK = 7;
    /**
     * 常量6.
     */
    private static final int CONSTANT_6 = 6;
    /**
     * 常量1.
     */
    private static final int CONSTANT_1 = 1;

    /**
     * 常量7.
     */
    private static final int CONSTANT_7 = 1;

    /**
     * 常量10.
     */
    private static final int CONSTANT_10 = 10;

    /**
     * 时间间隔正则表达式对象.
     * <p>
     * group(4) -> 日
     * group(5) -> 时
     * group(6) -> 分
     * group(7) -> 秒
     */
    private static final Pattern PATTERN_INTERVAL = compile("((((\\d+) )?(\\d+):)?(\\d+):)?(\\d+)$");

    /**
     * 时间间隔表达式的各个间隔定义.
     */
    private static final int[] PARTS_INTERVAL = new int[]{
            1, 24, 60, 60};

    /**
     * PATTERN_INTERVAL的groups与PARTS_INTERVAL之间的偏移量.
     */
    private static final int PARTS_INTERVAL_OFFSET = 4;

    /**
     * 1秒对应的毫秒数.
     */
    public static final long ONE_SECOND_IN_MILLIS = 1000;

    /**
     * 1分钟对应的毫秒数.
     */
    public static final long ONE_MINUTE_IN_MILLIS = 60 * ONE_SECOND_IN_MILLIS;

    /**
     * 1小时对应的毫秒数.
     */
    public static final long ONE_HOUR_IN_MILLIS = 60 * ONE_MINUTE_IN_MILLIS;

    /**
     * 1天对应的毫秒数.
     */
    public static final long ONE_DAY_IN_MILLIS = 24 * ONE_HOUR_IN_MILLIS;

    /**
     * 获取当前线程的时间（并非线程开始执行的时间，只是控制同一线程获得的时间一致）.
     *
     * @return Date对象
     */
    public static Date getThreadDate() {
        return new Date();
    }

    /**
     * 获取当前事务的时间（并非事务开始执行的时间，只是控制同一事务获得的时间一致）.
     *
     * @return Date对象
     */
    public static Date getTransactionDate() {
        // TODO 需要实现成事务范围内统一的时间
        return getThreadDate();
    }

    /**
     * 时间转换.
     *
     * @param dateStr 时间
     * @return timestamp
     */
    public static Timestamp stringToTimestamp(final String dateStr) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = getInstance();
        try {
            Date date = sdf.parse(dateStr);
            cal.setTime(date);
            return new Timestamp(cal.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
            cal.setTime(new Date());
            return new Timestamp(cal.getTimeInMillis());
        }
    }

    /**
     * 要隐藏的构造函数.
     */
    private DateUtils() {
    }

    /**
     * 解析时间/日期字符串.
     *
     * @param pattern 格式
     * @param source  时间/日期字符串
     * @return Date
     */
    public static Date parse(final String pattern, final String source) {
        try {
            return new SimpleDateFormat(pattern, Locale.US).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 解析时间/日期字符串,格式：yyyy-MM-dd HH:mm:ss.
     *
     * @param source 时间/日期字符串
     * @return Date
     */
    public static Date parseTimestamp(final String source) {
        try {
            return new SimpleDateFormat(PATTERN_TIMESTAMP).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 解析日期字符串,格式：yyyy-MM-dd.
     *
     * @param source 日期字符串
     * @return Date
     */
    public static Date parseDate(final String source) {
        try {
            return new SimpleDateFormat(PATTERN_DATE).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 解析时间字符串,格式：HH:mm:ss.
     *
     * @param source 时间字符串
     * @return Date
     */
    public static Date parseTime(final String source) {
        try {
            return new SimpleDateFormat(PATTERN_TIME).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 解析时间字符串,格式：yyyy-MM-dd HH:mm:ss.SSS.
     *
     * @param source 时间字符串
     * @return Date
     */
    public static Date parseFull(final String source) {
        try {
            return new SimpleDateFormat(PATTERN_FULL).parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化Date.
     *
     * @param pattern 格式
     * @param date    date对象
     * @return String 格式化日期字符串
     */
    public static String format(final String pattern, final Date date) {
        return new SimpleDateFormat(pattern, Locale.US).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd HH:mm:ss.
     *
     * @param date date对象
     * @return String
     */
    public static String formatTimestamp(final Date date) {
        return new SimpleDateFormat(PATTERN_TIMESTAMP).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd.
     *
     * @param date date对象
     * @return String
     */
    public static String formatDate(final Date date) {
        return new SimpleDateFormat(PATTERN_DATE).format(date);
    }

    /**
     * 格式化Date,格式：HH:mm:ss.
     *
     * @param date date对象
     * @return String
     */
    public static String formatTime(final Date date) {
        return new SimpleDateFormat(PATTERN_TIME).format(date);
    }

    /**
     * 格式化Date,格式：yyyy-MM-dd HH:mm:ss.SSS.
     *
     * @param date date对象
     * @return String
     */
    public static String formatFull(final Date date) {
        return new SimpleDateFormat(PATTERN_FULL).format(date);
    }

    /**
     * 格式化时间/日期字符串.
     *
     * @param outPatt 输出格式
     * @param inPatt  输入格式(与source对应)
     * @param source  时间/日期字符串(与inPatt对应)
     * @return String
     */
    public static String format(final String outPatt, final String inPatt, final String source) {
        return format(outPatt, parse(inPatt, source));
    }

    /**
     * 按指定格式取得当前日期(时间).
     *
     * @param pattern 格式
     * @return String
     */
    public static String getTimestamp(final String pattern) {
        return format(pattern, new Date());
    }

    /**
     * 计算两个日期年份的差值.
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 年份差值
     */
    public static int calDValueOfYear(final Date fromDate, final Date toDate) {
        Calendar sCal = getInstance();
        Calendar eCal = getInstance();
        sCal.setTime(fromDate);
        eCal.setTime(toDate);

        return eCal.get(YEAR) - sCal.get(YEAR);
    }

    /**
     * 计算两个日期月份的差值.
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 月份差值
     */
    public static long calDValueOfMonth(final Date fromDate, final Date toDate) {
        Calendar sCal = getInstance();
        Calendar eCal = getInstance();
        sCal.setTime(fromDate);
        eCal.setTime(toDate);
        return ONE_YEAR * (eCal.get(YEAR) - sCal.get(YEAR))
                + (eCal.get(MONTH) - sCal.get(MONTH));
    }

    /**
     * 计算两个日期的差值.
     *
     * @param fromDate 起始日期
     * @param toDate   截止日期
     * @return 相差天数
     */
    public static int calDValueOfDay(final Date fromDate, final Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / ONE_DAY_IN_MILLIS);
    }

    /**
     * 根据日期取得所在月的第一天.
     *
     * @param date 日期
     * @return Date 月第一天
     */
    public static Date getFirstDayOfMonth(final Date date) {
        Calendar calendar = getInstance();

        calendar.setTime(date);
        calendar.set(DAY_OF_MONTH, 1);
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的第一天(周第一天为周一).
     *
     * @param date 日期
     * @return Date 周第一天
     */
    public static Date getFirstDayOfWeek(final Date date) {
        return getFirstDayOfWeek(date, MONDAY);
    }

    /**
     * 根据日期取得所在周的最后一天(周第一天为周一).
     *
     * @param date 日期
     * @return Date 周最后一天
     */
    public static Date getLastDayOfWeek(final Date date) {
        return getLastDayOfWeek(date, MONDAY);
    }

    /**
     * 根据日期取得所在周的第一天.
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date 周第一天
     */
    public static Date getFirstDayOfWeek(final Date date, final int firstDayOfWeek) {
        Calendar calendar = getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的最后一天.
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date 周最后一天
     */
    public static Date getLastDayOfWeek(final Date date, final int firstDayOfWeek) {
        Calendar calendar = getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);

        calendar.add(DAY_OF_YEAR, ONE_WEEK);
        calendar.add(MILLISECOND, -CONSTANT_1);

        return calendar.getTime();
    }

    /**
     * 根据日期取得所在周的第一天和最后一天(周第一天为周一).
     *
     * @param date 日期
     * @return Date[]
     */
    public static Date[] getWeek(final Date date) {
        return getWeek(date, MONDAY);
    }

    /**
     * 根据日期取得所在周的第一天和最后一天(周第一天为周一).
     *
     * @param date           日期
     * @param firstDayOfWeek 周第一天(如Calendar.SUNDAY为周日,Calendar.MONDAY为周一)
     * @return Date[]
     */
    public static Date[] getWeek(final Date date, final int firstDayOfWeek) {
        Calendar calendar = getInstance();
        calendar.setFirstDayOfWeek(firstDayOfWeek);

        calendar.setTime(date);
        calendar.set(DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(HOUR_OF_DAY, 0);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        Date firstDate = calendar.getTime();

        calendar.add(DAY_OF_YEAR, ONE_WEEK);
        calendar.add(MILLISECOND, -CONSTANT_1);
        Date lastDate = calendar.getTime();
        return new Date[]{firstDate, lastDate};
    }

    /**
     * 将java.util.Date对象转换成java.sql.Date对象.
     *
     * @param date java.util.Date
     * @return java.sql.Date
     */
    public static java.sql.Date toSQLDate(final Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取当前时间(java.util.Date).
     *
     * @return java.util.Date
     */
    public static java.sql.Date getSQLDate() {
        return new java.sql.Date(currentTimeMillis());
    }

    /**
     * 加减Date各字段的值.
     *
     * @param date      Date
     * @param field     字段,如Calendar.DAY_OF_YEAR,Calendar.MINUTE等
     * @param increment 增量,可为负值
     * @return Date
     */
    public static Date add(final Date date, final int field, final int increment) {
        Calendar cal = getInstance();
        cal.setTime(date);

        cal.add(field, increment);
        return cal.getTime();
    }

    /**
     * 通过年月获得上个月的字符串 strFormat.
     *
     * @param startTime string"yyyy-MM"
     * @param strFormat String 转换格式格式
     * @return String "yyyy-(MM-1)"
     */
    public static String getPreMonTime(final String startTime, final String strFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
            Calendar cal = getInstance();
            cal.setTime(sdf.parse(startTime));
            cal.add(MONTH, -CONSTANT_1);
            return sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 在日期的前 几周 的周一和周日 的字付日期 （格式yyyy-MM-dd）.
     *
     * @param weekTimes  日期的前几周 的周一和周日
     * @param reportDate .
     * @return String[周一，周日]
     */
    public static String[] getWeekStartEnd(final int weekTimes, final String reportDate) {
        String[] startEnd = new String[2];
        Calendar cal = getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(reportDate);
        } catch (Exception e) {
        }
        cal.setTime(date);
        int intervalDay = cal.get(DAY_OF_WEEK); // 得到日期是一周的第几天
        int startDay = (intervalDay - CONSTANT_1) + (ONE_WEEK * (weekTimes - CONSTANT_1) + CONSTANT_6); // 周日算第一天
        // intervalDay
        // - 1
        cal.add(DATE, -startDay);
        startEnd[0] = sdf.format(cal.getTime());
        cal.add(DATE, CONSTANT_6); // 把日期往后增加6天 即 周日的日期. 整数往后推,负数往前移动
        startEnd[1] = sdf.format(cal.getTime());
        // System.out.println("weekEnd :"+sdf.format(cal.getTime()));
        return startEnd;
    }

    /**
     * 如strDate日期大于当前日期则返回当前字符日期，否则返回 strDate.
     *
     * @param strDate 字符日期
     * @param format  日期格式
     * @return 字符日期
     */
    public static String getEndDate(final String strDate, final String format) {
        Calendar cal = getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(strDate);
            cal.setTime(date);
            Calendar calNow = getInstance();
            calNow.setTime(new Date());
            if (cal.getTimeInMillis() > calNow.getTimeInMillis()) {
                return sdf.format(new Date());
            } else {
                return strDate;
            }
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取年份.
     *
     * @param start 日期
     * @return String
     */
    public static int getYear(final Date start) {
        Calendar cal = getInstance();
        cal.setTime(start);
        return cal.get(YEAR);
    }

    /**
     * 获取周.
     *
     * @param start 日期
     * @return String
     */
    public static int getWeekForYear(final Date start) {
        Calendar cal = getInstance();
        cal.setTime(start);
        cal.setFirstDayOfWeek(MONDAY);
        return cal.get(WEEK_OF_YEAR);
    }

    /**
     * 获取上X周是当年的第几周.
     *
     * @param start 日期
     * @param i     前几周
     * @return 周数
     */
    public static int getWeekDays(final Date start, final int i) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(MONDAY);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = cal.get(DAY_OF_WEEK); // 获得当前日期是一个星期的第几天
        if (1 == dayOfWeek) {
            cal.add(DAY_OF_MONTH, -1);
        }

        // 获得当前日期是一个星期的第几天
        int day = cal.get(DAY_OF_WEEK);

        //获取当前日期前（下）x周同星几的日期
        cal.add(DATE, CONSTANT_7 * i);

        cal.add(DATE, cal.getFirstDayOfWeek() - day);

        return cal.get(WEEK_OF_YEAR);

    }


    /**
     * 获取月份.
     *
     * @param start 日期
     * @return int
     */
    public static int getMonth(final Date start) {
        Calendar cal = getInstance();
        cal.setTime(start);
        return cal.get(MONTH) + CONSTANT_1;
    }

    /**
     * 获取当前月份.
     *
     * @param start 日期
     * @return int
     */
    public static String getMonthString(final Date start) {
        Calendar cal = getInstance();
        cal.setTime(start);
        cal.add(MONTH, 0);
        return new SimpleDateFormat(PATTERN_MONTH).format(cal.getTime());
    }

    /**
     * 获取上X天日期（yyyy-mm-dd）.
     *
     * @param start 日期
     * @param i     第几天
     * @return int
     */
    public static String getDayString(final Date start, final int i) {
        Calendar cal = getInstance();
        cal.setTime(start);
        cal.add(DATE, i);
        return new SimpleDateFormat(PATTERN_DATE).format(cal.getTime());
    }

    /**
     * 获取上X月份.
     *
     * @param start 日期
     * @param i     上几月
     * @return String
     */
    public static String getBackMonthString(final Date start, final int i) {
        Calendar cal = getInstance();
        cal.setTime(start);
        cal.add(MONTH, i);
        Date m = cal.getTime();
        return new SimpleDateFormat(PATTERN_DATE).format(m);

    }

    /**
     * 日期.
     *
     * @param start 日期
     * @return int
     */
    public static int getDay(final Date start) {
        Calendar cal = getInstance();
        cal.setTime(start);
        return cal.get(DAY_OF_MONTH);
    }

    /**
     * 日期所在月的总天数.
     *
     * @param start 日期
     * @return int
     */
    public static int getMonDays(final Date start) {
        Calendar time = getInstance();
        time.setTime(start);
        int year = time.get(YEAR); //当前年份
        int month = time.get(MONTH); // 当前月份的上一月(Calendar对象默认一月为0)
        time.clear();
        time.set(YEAR, year);
        time.set(MONTH, month); // Calendar对象默认一月为0
        int dayCount = time.getActualMaximum(DAY_OF_MONTH); // 本月份的天数
        return dayCount;
    }

    /**
     * 在日期 start上 增加 天 days ，返回增加天之后的日期.
     *
     * @param start 起始日期
     * @param days  增加天数
     * @return Date
     */
    public static Date getDateAddDays(final Date start, final int days) {
        Calendar ca = getInstance();
        ca.setTime(start);
        ca.add(DATE, days);
        return ca.getTime();
    }

    /**
     * 在日期 start上 增加 天months ，返回增加月之后的日期.
     *
     * @param start  起始月份
     * @param months 增加的月份数
     * @return Date
     */
    public static Date getDateAddMonths(final Date start, final int months) {
        Calendar ca = getInstance();
        ca.setTime(start);
        ca.add(MONTH, months);
        return ca.getTime();
    }

    /**
     * 获取某年最后一天日期.
     *
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearLast(final int year) {
        Calendar calendar = getInstance();
        calendar.clear();
        calendar.set(YEAR, year);
        calendar.roll(DAY_OF_YEAR, -CONSTANT_1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    /**
     * 当前时间所在月的第一天的date日期.
     *
     * @param date 日期
     * @return Date
     */
    public static Date firstDayOfMonth(final Date date) {
        Calendar calendar = getInstance();
        calendar.set(DAY_OF_MONTH,
                calendar.getActualMinimum(DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当月的最后一天.
     *
     * @param date 日期
     * @return Data对象
     */
    public static Date lastDayOfMonth(final Date date) {
        Calendar calendar = getInstance();
        calendar.set(DAY_OF_MONTH,
                calendar.getActualMaximum(DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 获取当前time.
     *
     * @return long
     */
    public static long getTime() {
        Calendar calendar = getInstance();
        return calendar.getTimeInMillis();
    }

    /**
     * 将日期指定的field置0.
     *
     * @param calendar 日期
     * @param fields   fileds，参考Calendar的相关常量
     */
    private static void zeroFields(final Calendar calendar, final int... fields) {
        if (fields == null) {
            return;
        }

        for (int field : fields) {
            calendar.set(field, 0);
        }
    }

    /**
     * 将日期截取到天.
     *
     * @param date 日期
     * @return 截取后的日期对象.
     */
    public static Date truncateDate(final Date date) {
        Calendar calendar = getInstance();
        zeroFields(calendar, MILLISECOND, SECOND, MINUTE, HOUR_OF_DAY);
        return calendar.getTime();
    }

    /**
     * 将日期截取到月（当月的首日）.
     *
     * @param date 日期
     * @return 截取后的日期对象
     */
    public static Date truncateMonth(final Date date) {
        Calendar calendar = getInstance();
        zeroFields(calendar, MILLISECOND, SECOND, MINUTE, HOUR_OF_DAY);
        calendar.set(DATE, 1);
        return calendar.getTime();
    }

    /**
     * 将日期截取到年（当年的首日）.
     *
     * @param date 日期
     * @return 截取后的日期对象
     */
    public static Date truncateYear(final Date date) {
        Calendar calendar = getInstance();
        zeroFields(calendar, MILLISECOND, SECOND, MINUTE, HOUR_OF_DAY);
        calendar.set(DATE, 1);
        calendar.set(MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取TruncateDate.
     *
     * @param date  日期
     * @param field 时间类型，只支持Calendar.DATE、Calendar.MONTH、Calendar.YEAR
     * @return Date
     */
    public static Date getTruncateDate(final Date date, final int field) {
        if (field == DATE) {
            return truncateDate(date);
        } else if (field == MONTH) {
            return truncateMonth(date);
        } else if (field == YEAR) {
            return truncateYear(date);
        }

        return date;
    }

    /**
     * 获取当月最后一日.
     *
     * @param date 日期
     * @return Date
     */
    public static Date lastTimestampOfDate(final Date date) {
        int year = DateUtils.getYear(date);
        int month = DateUtils.getMonth(date);
        //结束时间是本月最后一天
        Date endTime = new Date(DateUtils.parse(
                "yyyy-MM-dd HH:mm:ss",
                year + "-" + month + "-"
                        + (DateUtils.getMonDays(date))
                        + " 23:59:59").getTime());
        return endTime;
    }

    /**
     * 将时间间隔表达式转化为秒数.
     *
     * @param intervalExpr 时间间隔的表达式，格式必须是：d+ H+:m+:s+(具体参照：PATTERN_INTERVAL)
     * @return 秒数
     */
    public static long intervalToSeconds(final String intervalExpr) {
        Matcher mat = PATTERN_INTERVAL.matcher(intervalExpr);

        String group;
        long count = 0;

        if (!mat.find()) {
            return count;
        }

        for (int i = 0; i < PARTS_INTERVAL.length; i++) {
            count *= PARTS_INTERVAL[i];
            group = mat.group(i + PARTS_INTERVAL_OFFSET);

            if (group != null) {
                count += toInt(group);
            }
        }

        return count;
    }

    /**
     * 根据周的时间段，获取是一年中的第几周.
     *
     * @param time 时间段
     * @return String 第几周（yyyy-week）
     */
    public static String getWeekInYear(final String time) {
        String result = "";
        if (StringUtil.hasText(time)) {
            String firstWeekDay = time.substring(0, CONSTANT_10); //截取当周的的第一天日期
            String year = String.valueOf(DateUtils.getYear(DateUtils.parseDate(firstWeekDay))); //当年(yyyy)
            result = year + "-" + String.valueOf(DateUtils.getWeekForYear(DateUtils.parseDate(firstWeekDay)));
        }
        return result;
    }

}
