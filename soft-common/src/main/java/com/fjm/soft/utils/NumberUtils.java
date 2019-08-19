/*
 * Project:    全课通平台 - 通用工具模块
 *
 * FileName:   NumberUtils.java
 * CreateTime: 2018-08-01 10:17:48
 */
package com.fjm.soft.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * @Author: fongjinming
 * @CreateTime: 2019-08-19 19:17
 * @Description:
 */
public final class NumberUtils {

    /**
     * 转换成double（支持多种输入类型：Number、String）.
     *
     * @param val 输入对象
     * @return double
     */
    public static double toDouble(final Object val) {
        if (val instanceof Number) {
            return ((Number) val).doubleValue();
        } else if (val instanceof String) {
            // TODO 可能还需要处理千分位符
            return parseDouble((String) val);
        } else {
            throw new RuntimeException("并非所有对象都能toDouble的");
        }
    }

    /**
     * 转换成long（支持多种输入类型：Number、String）.
     *
     * @param val 输入对象
     * @return long
     */
    public static long toLong(final Object val) {
        if (val instanceof Number) {
            return ((Number) val).longValue();
        } else if (val instanceof String) {
            // TODO 可能还需要处理千分位符
            return parseLong((String) val);
        } else {
            throw new RuntimeException("并非所有对象都能toLong的");
        }
    }

    /**
     * 转换成int（支持多种输入类型：Number、String）.
     *
     * @param val 输入对象
     * @return int
     */
    public static int toInt(final Object val) {
        if (val instanceof Number) {
            return ((Number) val).intValue();
        } else if (val instanceof String) {
            // TODO 可能还需要处理千分位符
            return parseInt((String) val);
        } else {
            throw new RuntimeException("并非所有对象都能toLong的");
        }
    }

    /**
     * 生成随机正数.
     *
     * @return int
     */
    public static Integer getRandomInt() {
        Random r = new Random();
        int randomInt = r.nextInt();
        if (randomInt > 0) {
            return randomInt;
        } else {
            return randomInt * -1;
        }
    }

    /**
     * 两数相除，返回X小数位的百分数.
     *
     * @param top   分子
     * @param below 分母
     * @param i     小数位数
     * @return String 百分数
     */
    public static String decimal(final int top, final int below, final int i) {
        String result = "0.00";
        //获取格式化对象
        DecimalFormat df = new DecimalFormat("0.00");
        if (top > 0 && below > 0) {
            result = df.format((float) top / below);
        }
        return result;
    }

    /**
     * 两数相除，返回X小数位的百分数.
     *
     * @param top   分子
     * @param below 分母
     * @param i     小数位数
     * @return String 百分数
     */
    public static String pMal(final int top, final int below, final int i) {
        String result = "0%";
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度i即保留i位小数
        nt.setMinimumFractionDigits(i);
        DecimalFormat df = new DecimalFormat("0.00");
        if (top > 0 && below > 0) {
            result = nt.format(df.format((float) top / below));
        }
        return result;
    }


    /**
     * 隐藏构造函数.
     */
    private NumberUtils() {
    }

}
