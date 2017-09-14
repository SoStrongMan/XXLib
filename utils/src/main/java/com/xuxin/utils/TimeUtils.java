package com.xuxin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import static com.xuxin.utils.ConstUtils.DAY;
import static com.xuxin.utils.ConstUtils.HOUR;
import static com.xuxin.utils.ConstUtils.MIN;
import static com.xuxin.utils.ConstUtils.MSEC;
import static com.xuxin.utils.ConstUtils.SEC;

/**
 * 时间相关的工具类
 */
public class TimeUtils {

    private TimeUtils() {
        throw new UnsupportedOperationException("You do not need to instantiate!");
    }

    /**
     * <p>在工具类中经常使用到工具类的格式化描述，这个主要是一个日期的操作类，所以日志格式主要使用 SimpleDateFormat的定义格式.</p>
     * 格式的意义如下： 日期和时间模式 <br>
     * <p>日期和时间格式由日期和时间模式字符串指定。在日期和时间模式字符串中，未加引号的字母 'A' 到 'Z' 和 'a' 到 'z'
     * 被解释为模式字母，用来表示日期或时间字符串元素。文本可以使用单引号 (') 引起来，以免进行解释。"''"
     * 表示单引号。所有其他字符均不解释；只是在格式化时将它们简单复制到输出字符串，或者在分析时与输入字符串进行匹配。
     * </p>
     * 定义了以下模式字母（所有其他字符 'A' 到 'Z' 和 'a' 到 'z' 都被保留）： <br>
     * <table border="1" cellspacing="1" cellpadding="1" summary="Chart shows pattern letters, date/time component, presentation, and examples.">
     * <tr bgcolor="#ccccff">
     * <th align="left">字母</th>
     * <th align="left">日期或时间元素</th>
     * <th align="left">表示</th>
     * <th align="left">示例</th>
     * </tr>
     * <tr>
     * <td><code>G</code></td>
     * <td>Era 标志符</td>
     * <td>Text</td>
     * <td><code>AD</code></td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>y</code> </td>
     * <td>年 </td>
     * <td>Year </td>
     * <td><code>1996</code>; <code>96</code> </td>
     * </tr>
     * <tr>
     * <td><code>M</code> </td>
     * <td>年中的月份 </td>
     * <td>Month </td>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>w</code> </td>
     * <td>年中的周数 </td>
     * <td>Number </td>
     * <td><code>27</code> </td>
     * </tr>
     * <tr>
     * <td><code>W</code> </td>
     * <td>月份中的周数 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>D</code> </td>
     * <td>年中的天数 </td>
     * <td>Number </td>
     * <td><code>189</code> </td>
     * </tr>
     * <tr>
     * <td><code>d</code> </td>
     * <td>月份中的天数 </td>
     * <td>Number </td>
     * <td><code>10</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>F</code> </td>
     * <td>月份中的星期 </td>
     * <td>Number </td>
     * <td><code>2</code> </td>
     * </tr>
     * <tr>
     * <td><code>E</code> </td>
     * <td>星期中的天数 </td>
     * <td>Text </td>
     * <td><code>Tuesday</code>; <code>Tue</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>a</code> </td>
     * <td>Am/pm 标记 </td>
     * <td>Text </td>
     * <td><code>PM</code> </td>
     * </tr>
     * <tr>
     * <td><code>H</code> </td>
     * <td>一天中的小时数（0-23） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>k</code> </td>
     * <td>一天中的小时数（1-24） </td>
     * <td>Number </td>
     * <td><code>24</code> </td>
     * </tr>
     * <tr>
     * <td><code>K</code> </td>
     * <td>am/pm 中的小时数（0-11） </td>
     * <td>Number </td>
     * <td><code>0</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>h</code> </td>
     * <td>am/pm 中的小时数（1-12） </td>
     * <td>Number </td>
     * <td><code>12</code> </td>
     * </tr>
     * <tr>
     * <td><code>m</code> </td>
     * <td>小时中的分钟数 </td>
     * <td>Number </td>
     * <td><code>30</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>s</code> </td>
     * <td>分钟中的秒数 </td>
     * <td>Number </td>
     * <td><code>55</code> </td>
     * </tr>
     * <tr>
     * <td><code>S</code> </td>
     * <td>毫秒数 </td>
     * <td>Number </td>
     * <td><code>978</code> </td>
     * </tr>
     * <tr bgcolor="#eeeeff">
     * <td><code>z</code> </td>
     * <td>时区 </td>
     * <td>General time zone </td>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>; <code>GMT-08:00</code> </td>
     * </tr>
     * <tr>
     * <td><code>Z</code> </td>
     * <td>时区 </td>
     * <td>RFC 822 time zone </td>
     * <td><code>-0800</code> </td>
     * </tr>
     * </table>
     * <pre>
     *                          HH:mm    15:44
     *                         h:mm a    3:44 下午
     *                        HH:mm z    15:44 CST
     *                        HH:mm Z    15:44 +0800
     *                     HH:mm zzzz    15:44 中国标准时间
     *                       HH:mm:ss    15:44:40
     *                     yyyy-MM-dd    2016-08-12
     *               yyyy-MM-dd HH:mm    2016-08-12 15:44
     *            yyyy-MM-dd HH:mm:ss    2016-08-12 15:44:40
     *       yyyy-MM-dd HH:mm:ss zzzz    2016-08-12 15:44:40 中国标准时间
     *  EEEE yyyy-MM-dd HH:mm:ss zzzz    星期五 2016-08-12 15:44:40 中国标准时间
     *       yyyy-MM-dd HH:mm:ss.SSSZ    2016-08-12 15:44:40.461+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     *   yyyy.MM.dd G 'at' HH:mm:ss z    2016.08.12 公元 at 15:44:40 CST
     *                         K:mm a    3:44 下午
     *               EEE, MMM d, ''yy    星期五, 八月 12, '16
     *          hh 'o''clock' a, zzzz    03 o'clock 下午, 中国标准时间
     *   yyyyy.MMMMM.dd GGG hh:mm aaa    02016.八月.12 公元 03:44 下午
     *     EEE, d MMM yyyy HH:mm:ss Z    星期五, 12 八月 2016 15:44:40 +0800
     *                  yyMMddHHmmssZ    160812154440+0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ    2016-08-12T15:44:40.461+0800
     * EEEE 'DATE('yyyy-MM-dd')' 'TIME('HH:mm:ss')' zzzz    星期五 DATE(2016-08-12) TIME(15:44:40) 中国标准时间
     * </pre>
     */
    public static final SimpleDateFormat DEFAULT_SDF1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF3 = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF4 = new SimpleDateFormat("MM.dd HH:mm", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF5 = new SimpleDateFormat("HH:mm", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF6 = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF7 = new SimpleDateFormat("yyyy", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF8 = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF9 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF10 = new SimpleDateFormat("MM-dd", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF11 = new SimpleDateFormat("M月d日，HH:mm", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF12 = new SimpleDateFormat("yyyy年M月d日", Locale.getDefault());
    public static final SimpleDateFormat DEFAULT_SDF13 = new SimpleDateFormat("MM/dd", Locale.getDefault());

    /**
     * 一分钟的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MINUTE = 60 * 1000;

    /**
     * 一小时的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_HOUR = 60 * ONE_MINUTE;

    /**
     * 一天的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_DAY = 24 * ONE_HOUR;

    /**
     * 一月的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_MONTH = 30 * ONE_DAY;

    /**
     * 一年的毫秒值，用于判断上次的更新时间
     */
    public static final long ONE_YEAR = 12 * ONE_MONTH;


    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param milliseconds 毫秒时间戳
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds) {
        return milliseconds2String(milliseconds, DEFAULT_SDF1);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param milliseconds 毫秒时间戳
     * @param format       时间格式
     * @return 时间字符串
     */
    public static String milliseconds2String(long milliseconds, SimpleDateFormat format) {
        if (milliseconds == 0) {
            return "";
        }
        return format.format(new Date(milliseconds));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time) {
        return string2Milliseconds(time, DEFAULT_SDF1);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Milliseconds(String time, SimpleDateFormat format) {
        try {
            return format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_SDF1);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>格式为用户自定义</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Milliseconds(time, format));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date time) {
        return date2String(time, DEFAULT_SDF1);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为用户自定义</p>
     *
     * @param time   Date类型时间
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date time, SimpleDateFormat format) {
        return format.format(time);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param time Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Milliseconds(Date time) {
        return time.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param milliseconds 毫秒时间戳
     * @return Date类型时间
     */
    public static Date milliseconds2Date(long milliseconds) {
        return new Date(milliseconds);
    }

    /**
     * 毫秒时间戳单位转换（单位：unit）
     *
     * @param milliseconds 毫秒时间戳
     * @param unit         <ul>
     *                     <li>MSEC:毫秒</li>
     *                     <li>SEC :秒</li>
     *                     <li>MIN :分</li>
     *                     <li>HOUR:小时</li>
     *                     <li>DAY :天</li>
     *                     </ul>
     * @return unit时间戳
     */
    private static long milliseconds2Unit(long milliseconds, int unit) {
        switch (unit) {
            case MSEC:
            case SEC:
            case MIN:
            case HOUR:
            case DAY:
                return milliseconds / unit;
        }
        return -1;
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串1
     * @param time1 时间字符串2
     * @param unit  <ul>
     *              <li>MSEC:毫秒</li>
     *              <li>SEC :秒</li>
     *              <li>MIN :分</li>
     *              <li>HOUR:小时</li>
     *              <li>DAY :天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, int unit) {
        return getIntervalTime(time0, time1, unit, DEFAULT_SDF1);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2格式都为format</p>
     *
     * @param time0  时间字符串1
     * @param time1  时间字符串2
     * @param unit   <ul>
     *               <li>MSEC:毫秒</li>
     *               <li>SEC :秒</li>
     *               <li>MIN :分</li>
     *               <li>HOUR:小时</li>
     *               <li>DAY :天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalTime(String time0, String time1, int unit, SimpleDateFormat format) {
        return Math.abs(milliseconds2Unit(string2Milliseconds(time0, format)
                - string2Milliseconds(time1, format), unit));
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time1和time2都为Date类型</p>
     *
     * @param time0 Date类型时间1
     * @param time1 Date类型时间2
     * @param unit  <ul>
     *              <li>MSEC:毫秒</li>
     *              <li>SEC :秒</li>
     *              <li>MIN :分</li>
     *              <li>HOUR:小时</li>
     *              <li>DAY :天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getIntervalTime(Date time0, Date time1, int unit) {
        return Math.abs(milliseconds2Unit(date2Milliseconds(time1)
                - date2Milliseconds(time0), unit));
    }

    /**
     * 获取当前时间
     *
     * @return 毫秒时间戳
     */
    public static long getCurTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getCurTimeString() {
        return date2String(new Date());
    }

    /**
     * 获取当前时间
     * <p>格式为用户自定义</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getCurTimeString(SimpleDateFormat format) {
        return date2String(new Date(), format);
    }

    /**
     * 获取当前时间
     * <p>Date类型</p>
     *
     * @return Date类型时间
     */
    public static Date getCurTimeDate() {
        return new Date();
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit <ul>
     *             <li>MSEC:毫秒</li>
     *             <li>SEC :秒</li>
     *             <li>MIN :分</li>
     *             <li>HOUR:小时</li>
     *             <li>DAY :天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, int unit) {
        return getIntervalByNow(time, unit, DEFAULT_SDF1);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为format</p>
     *
     * @param time   时间字符串
     * @param unit   <ul>
     *               <li>MSEC:毫秒</li>
     *               <li>SEC :秒</li>
     *               <li>MIN :分</li>
     *               <li>HOUR:小时</li>
     *               <li>DAY :天</li>
     *               </ul>
     * @param format 时间格式
     * @return unit时间戳
     */
    public static long getIntervalByNow(String time, int unit, SimpleDateFormat format) {
        return getIntervalTime(getCurTimeString(), time, unit, format);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time为Date类型</p>
     *
     * @param time Date类型时间
     * @param unit <ul>
     *             <li>MSEC:毫秒</li>
     *             <li>SEC :秒</li>
     *             <li>MIN :分</li>
     *             <li>HOUR:小时</li>
     *             <li>DAY :天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getIntervalByNow(Date time, int unit) {
        return getIntervalTime(getCurTimeDate(), time, unit);
    }

    /**
     * 判断闰年
     *
     * @param year 年份
     * @return true: 闰年<br>false: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 几天是多少毫秒
     *
     * @param days 天数
     * @return 毫秒数
     */
    public static long getIntervalByDay(long days) {
        return (int) (days * ONE_DAY);
    }

    /**
     * 指定时间星期几
     *
     * @param times 时间
     * @return 星期几
     */
    public static String getWeekByDay(String times) {
        String strDate = TimeUtils.milliseconds2String(Long.valueOf(times), TimeUtils.DEFAULT_SDF2);
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());// 定义日期格式
        try {
            date = format.parse(strDate);// 将字符串转换为日期
        } catch (ParseException e) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        return sdf.format(date);
    }

    /**
     * 相对于现在过去了多久
     *
     * @param time 时间
     * @return 时间
     */
    public static String refreshUpdatedAtValue(String time) {
        long lastUpdateTime = Long.valueOf(time);
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - lastUpdateTime;
        long timeIntoFormat;
        String updateAtValue;

        if (timePassed < 0) {
            updateAtValue = "已置顶";
        } else if (timePassed < ONE_MINUTE) {
            updateAtValue = "1小时内";
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            String value = timeIntoFormat + "分钟";
            updateAtValue = value;
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            String value = timeIntoFormat + "小时内";
            updateAtValue = value;
        }
//        else if (timePassed < ONE_MONTH) {
//            timeIntoFormat = timePassed / ONE_DAY;
//            String value = timeIntoFormat + "天";
//            updateAtValue = "1天前";
//        } else if (timePassed < ONE_YEAR) {
//            timeIntoFormat = timePassed / ONE_MONTH;
//            String value = timeIntoFormat + "个月";
//            updateAtValue = "1月前";
//        }
        else {
            try {
                Calendar cal = Calendar.getInstance();
                cal.setTimeInMillis(lastUpdateTime);
                Formatter ft = new Formatter(Locale.CHINA);
                updateAtValue = ft.format("%1$tY年%1$tm月%1$td日", cal).toString();
            } catch (Exception e) {
                updateAtValue = "时间解析错误";
            }
        }

        return updateAtValue;
    }

    /**
     * 相对于现在过去了多久
     *
     * @param time 时间
     * @return 时间
     */
    public static String passTimeString(long time) {
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - time;
        long timeIntoFormat;
        String updateAtValue;

        if (timePassed < ONE_MINUTE) {
            updateAtValue = "1分钟前";
        } else if (timePassed < ONE_HOUR) {
            timeIntoFormat = timePassed / ONE_MINUTE;
            updateAtValue = timeIntoFormat + "分钟前";
        } else if (timePassed < ONE_DAY) {
            timeIntoFormat = timePassed / ONE_HOUR;
            updateAtValue = timeIntoFormat + "小时内";
        } else if (timePassed < ONE_YEAR) {
            if (DEFAULT_SDF7.format(new Date(currentTime)).equals(DEFAULT_SDF7.format(new Date(time)))) {
                updateAtValue = DEFAULT_SDF6.format(new Date(time));
            } else {
                updateAtValue = DEFAULT_SDF2.format(new Date(time));
            }
        } else {
            updateAtValue = DEFAULT_SDF2.format(new Date(time));
        }

        return updateAtValue;
    }

    /**
     * 相对于现在过去了多久
     *
     * @param targetTime 目标时间
     * @return
     */
    public static String passTimeBefore(long targetTime) {

        long currentTime = System.currentTimeMillis();
        long passedTime = currentTime - targetTime;
        String updateAtValue;

        if (passedTime < ONE_MINUTE) {
            updateAtValue = "刚刚";
        } else if (passedTime < ONE_HOUR) {
            updateAtValue = passedTime / ONE_MINUTE + "分钟前";
        } else if (passedTime < ONE_DAY) {
            updateAtValue = passedTime / ONE_HOUR + "小时前";
        } else if (passedTime < ONE_MONTH) {
            updateAtValue = passedTime / ONE_DAY + "天前";
        } else if (passedTime < ONE_YEAR) {
            updateAtValue = milliseconds2String(targetTime, DEFAULT_SDF10);
        } else {
            updateAtValue = milliseconds2String(targetTime, DEFAULT_SDF2);
        }

        return updateAtValue;
    }

    /**
     * 判断时间超时
     *
     * @param time
     * @return
     */
    public static boolean checkTimeForUpdateUserInfo(String time) {
        boolean needUpdate = false;
        final long ONE_MINUTE = 60 * 1000;
        final long ONE_HOUR = 60 * ONE_MINUTE;
        final long ONE_DAY = 24 * ONE_HOUR;
        final long FIVE_DAY = 5 * 24 * ONE_HOUR;

        try {
            long lastUpdateTime = Long.valueOf(time);
            long currentTime = System.currentTimeMillis();
            long timePassed = currentTime - lastUpdateTime;

            if (timePassed < 0) {
                needUpdate = true;
            } else if (timePassed < ONE_MINUTE) {
                needUpdate = false;
            } else if (timePassed < ONE_HOUR) {
                needUpdate = false;
            } else if (timePassed < FIVE_DAY) {
                needUpdate = false;
            } else {
                needUpdate = true;
            }
        } catch (Exception e) {
            needUpdate = true;
        }
        return needUpdate;
    }

    /**
     * 获取今天0点的long型数据
     */
    public static Long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }
}

