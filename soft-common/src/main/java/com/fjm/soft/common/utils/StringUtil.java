package com.fjm.soft.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 对bean操作的工具库.字符串处理工具.
 *
 * @Author: fongjinming
 * @CreateTime: 2019-08-19 19:17
 * @Description:
 */
public final class StringUtil {

    /**
     * 日志.
     */
    private static Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 判断对象是否为空.
     *
     * @param obj 待判断对象
     * @return boolean true-空(null/空字符串/纯空白字符),false-非空
     */
    public static boolean isBlank(final Object obj) {
        return obj == null || obj.toString().trim().length() == 0;
    }

    /**
     * 判断字符串是否包含内容(与isBlank方法相反).
     *
     * @param obj 待判断对象
     * @return boolean true-有内容,false-空(null/空字符串/纯空白字符)
     */
    public static boolean hasText(final Object obj) {
        return !isBlank(obj);
    }

    /**
     * 判断是否为数字.
     *
     * @param obj 待判断对象
     * @return boolean true-是数字，false-不是字符串
     */
    public static boolean isNumeric(final Object obj) {
        return hasText(obj) && trim(obj).matches("^-?\\d+$");
    }

    /**
     * 转换为字符串.
     *
     * @param obj 待转换对象
     * @return String obj为空时返回空字符串
     */
    public static String toString(final Object obj) {
        String result = "";
        if (obj != null) {
            result = obj.toString();
        }
        return result;
    }

    /**
     * 返回一个对象的字符串,多数是处理字符串的.
     *
     * @param obj 待处理对象
     * @return String 字符串
     */
    public static String trim(final Object obj) {
        String result = "";
        if (obj != null) {
            result = obj.toString().trim();
        }
        return result;
    }

    /**
     * 对比两个对象是否相同,当obj1==null或obj2==null时,返回false,否则返回 obj1.equals(obj2).
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return boolean true-相同，false-不相同
     */
    public static boolean equals(final Object obj1, final Object obj2) {
        boolean equals = false;

        if (obj1 != null && obj2 != null) {
            equals = obj1.equals(obj2);
        }

        return equals;
    }

    /**
     * 对比两个字符串是否相同(忽略大小写).
     * 当obj1==null或obj2==null时,返回false.
     * 否则返回 obj1.toString().equalsIgnoreCase(obj2.toString()).
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return boolean true-两字字符串一样，false-不一样
     */
    public static boolean equalsIgnoreCase(final Object obj1,
                                           final Object obj2) {
        boolean equals = false;
        if (obj1 != null && obj2 != null) {
            equals = obj1.toString().equalsIgnoreCase(obj2.toString());
        }
        return equals;
    }

    /**
     * 转换为整型(Integer).
     *
     * @param obj        待转换对象
     * @param defaultVal 默认值(无法转换时返回该值)
     * @return Integer 转换后的整型
     */
    public static Integer toInteger(final Object obj,
                                    final int defaultVal) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            logger.warn("parse integer failed : {}, defaultVal : {}", obj, defaultVal);
            return defaultVal;
        }
    }

    /**
     * 转换为整型(Integer).
     *
     * @param obj 待转换对象
     * @return Integer 转换后的整型
     */
    public static Integer toInteger(final Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            logger.warn("parse integer failed : {}", obj);
            return null;
        }
    }

    /**
     * 转换为长整型(Long).
     *
     * @param obj        待转换对象
     * @param defaultVal 默认值(无法转换时返回该值)
     * @return Long 转换后的长整型
     */
    public static Long toLong(final Object obj,
                              final long defaultVal) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            logger.warn("parse long failed : {}, defaultVal : {}", obj, defaultVal);
            return defaultVal;
        }
    }

    /**
     * 转换为长整型(Long).
     *
     * @param obj 待转换对象
     * @return Long 转换后的长整型
     */
    public static Long toLong(final Object obj) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            logger.warn("parse long failed : {}", obj);
            return null;
        }
    }

    /**
     * 转换为浮点数(Float).
     *
     * @param obj        待转换对象
     * @param defaultVal 默认值(无法转换时返回该值)
     * @return Float 转换后的浮点数
     */
    public static Float toFloat(final Object obj,
                                final float defaultVal) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            logger.warn("parse float failed : {}, defaultVal : {}", obj, defaultVal);
            return defaultVal;
        }
    }

    /**
     * 转换为浮点数(Float).
     *
     * @param obj 待转换对默认值(无法转换时返回该值)
     * @return Float 转换后的浮点数
     */
    public static Float toFloat(final Object obj) {
        try {
            return Float.parseFloat(obj.toString());
        } catch (Exception e) {
            logger.warn("parse float failed : {}", obj);
            return null;
        }
    }

    /**
     * 转换为双精度浮点数(Double).
     *
     * @param obj        待转换对象
     * @param defaultVal 默认值(无法转换时返回该值)
     * @return Double 转换后的双精度浮点数
     */
    public static Double toDouble(final Object obj,
                                  final double defaultVal) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            logger.warn("parse double failed : {}, defaultVal : {}", obj, defaultVal);
            return defaultVal;
        }
    }

    /**
     * 转换为双精度浮点数(Double).
     *
     * @param obj 待转换对象
     * @return Double 转换后的双精度浮点数
     */
    public static Double toDouble(final Object obj) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception e) {
            logger.warn("parse double failed : {}", obj);
            return null;
        }
    }

    /**
     * 字符串转换为List.
     *
     * @param obj       带转换的字符串
     * @param separator 分隔符
     * @return List<String>
     */
    public static List<String> toList(final Object obj,
                                      final String separator) {
        List<String> list = new ArrayList<>();
        if (hasText(obj)) {
            String[] temp = trim(obj).split(separator);
            for (String str : temp) {
                if (hasText(str)) {
                    list.add(str);
                }
            }
        }
        return list;
    }

    /**
     * 转换为List.
     *
     * @param obj 待转换的字符串
     * @return List<String> 转换后的List
     */
    public static List<String> toList(final Object obj) {
        return toList(obj, ",");
    }

    /**
     * 转换为数组.
     *
     * @param obj       待转换的字符串
     * @param separator 分隔符
     * @return String[] 转换后的数组
     */
    public static String[] toArray(final Object obj,
                                   final String separator) {
        return toList(obj, separator).toArray(new String[0]);
    }

    /**
     * 转换为String数组.
     *
     * @param obj 待转换的字符串
     * @return String[] 转换后的数组
     */
    public static String[] toArray(final Object obj) {
        return toList(obj, ",").toArray(new String[0]);
    }

    /**
     * 将字符串转换为Integer集合.
     *
     * @param obj       待转换字符串
     * @param separator 分隔符
     * @return List<Integer> 转换后的Integer集合
     */
    public static List<Integer> toIntList(final Object obj,
                                          final String separator) {
        List<Integer> list = new ArrayList<>();
        if (hasText(obj)) {
            String[] temp = trim(obj).split(separator);
            for (String str : temp) {
                Integer i = toInteger(str);
                if (i != null) {
                    list.add(i);
                }
            }
        }
        return list;
    }

    /**
     * 将字符串转换为Integer集合(分隔符:",").
     *
     * @param obj 待转换字符串
     * @return List<Integer> 转换后的Integer集合
     */
    public static List<Integer> toIntList(final Object obj) {
        return toIntList(obj, ",");
    }

    /**
     * 将字符串转换为Integer数组.
     *
     * @param obj       待转换字符串
     * @param separator 分隔符
     * @return Integer[] 转换后的Integer数组
     */
    public static Integer[] toIntArray(final Object obj,
                                       final String separator) {
        return toIntList(obj, separator).toArray(new Integer[0]);
    }

    /**
     * 将字符串转换为Integer数组(分隔符:",").
     *
     * @param obj 待转换字符串
     * @return Integer[] 转换后的Integer数组
     */
    public static Integer[] toIntArray(final Object obj) {
        return toIntList(obj, ",").toArray(new Integer[0]);
    }

    /**
     * 将字符串转换为Long集合.
     *
     * @param obj       待转换字符串
     * @param separator 分隔符
     * @return List<Long> 转换后的Long集合
     */
    public static List<Long> toLongList(final Object obj,
                                        final String separator) {
        List<Long> list = new ArrayList<Long>();
        if (hasText(obj)) {
            String[] temp = trim(obj).split(separator);
            for (String str : temp) {
                Long l = toLong(str);
                if (l != null) {
                    list.add(l);
                }
            }
        }
        return list;
    }

    /**
     * 将字符串转换为Long集合(分隔符:",").
     *
     * @param obj 待转换字符串
     * @return List<Long> 转换后的Long集合
     */
    public static List<Long> toLongList(final Object obj) {
        return toLongList(obj, ",");
    }

    /**
     * 将字符串转换为Long数组.
     *
     * @param obj       待转换字符串
     * @param separator 分隔符
     * @return Long[] 转换后的Long数组
     */
    public static Long[] toLongArray(final Object obj,
                                     final String separator) {
        return toLongList(obj, separator).toArray(new Long[0]);
    }

    /**
     * 将字符串转换为Long数组(分隔符:",").
     *
     * @param obj 待转换字符串
     * @return Long[] 转换后的Long数组
     */
    public static Long[] toLongArray(final Object obj) {
        return toLongList(obj, ",").toArray(new Long[0]);
    }

    /**
     * 转换为小写.
     *
     * @param obj 待转换字符串
     * @return 返回小写的字符串
     */
    public static String toLowerCase(final Object obj) {
        if (hasText(obj)) {
            return toString(obj).toLowerCase();
        } else {
            return null;
        }
    }

    /**
     * 转换为大写.
     *
     * @param obj 待转换字符串
     * @return 返回大写的字符串
     */
    public static String toUpperCase(final Object obj) {
        if (hasText(obj)) {
            return toString(obj).toUpperCase();
        } else {
            return null;
        }
    }

    /**
     * 方法描述 :  字符按照字节截取.
     * 创建者：an.K
     * 项目名称： yzt
     * 版本： v1.0
     * 创建时间： 2015-8-14 上午11:16:57
     *
     * @param separator 待截取的字符串
     * @param size      长度
     * @return String 返回截取后的字符串
     * @throws Exception 抛出异常
     */
    public static String toSubstring(final String separator,
                                     final Integer size)
            throws Exception {
        byte[] bytes = separator.getBytes("Unicode");
        int n = 0; // 表示当前的字节数
        int i = 2; // 要截取的字节数，从第3个字节开始
        for (; i < bytes.length && n < size; i++) {
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
            if (i % 2 == 1) {
                n++; // 在UCS2第二个字节时n加1
            } else {
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                if (bytes[i] != 0) {
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数
        if (i % 2 == 1) {
            // 该UCS2字符是汉字时，去掉这个截一半的汉字
            if (bytes[i - 1] != 0) {
                i = i - 1;
                // 该UCS2字符是字母或数字，则保留该字符
            } else {
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }

    /**
     * String字符串转List.
     *
     * @param str 字符串
     * @return List<String> 返回转换后的集合
     */
    public static List<String> getList(final String str) {
        List<String> strList = new ArrayList<>();
        if (StringUtil.hasText(str)) {
            String[] list = str.split(",");
            if (list.length > 0) {
                for (int i = 0; i < list.length; i++) {
                    if (StringUtil.hasText(list[i])) {
                        strList.add(list[i]);
                    }
                }
            }
        }
        return strList;
    }

    /**
     * 字符串是否存在List.
     *
     * @param str        待校验字符串
     * @param targetList 需要校验的List
     * @return boolean true 存在 false 不存在
     */
    public static boolean startsWithInList(final String str, final List<String> targetList) {
        boolean result = false;

        if (StringUtil.hasText(str) && targetList != null && targetList.size() > 0) {
            for (int i = 0; i < targetList.size(); i++) {
                String value = targetList.get(i);
                if (StringUtil.hasText(value) && str.startsWith(value)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * id转成集合.
     *
     * @param ids id（逗号分隔）
     * @return List<String>
     */
    public static List<String> idsToList(final String ids) {
        List<String> list = new ArrayList<String>();
        if (StringUtil.hasText(ids)) {
            String[] valueArr = ids.split(",");
            if (valueArr != null && valueArr.length > 0) {
                for (int j = 0; j < valueArr.length; j++) {
                    list.add(valueArr[j]);
                }
            }
        }
        return list;
    }

    /**
     * 校对 id是否存在大数据ids里面.
     *
     * @param ids 大数据ids（逗号隔开）
     * @param id  待校对的id
     * @return boolean true 存在 false 不存在
     */
    public static boolean isIn(final String ids, final String id) {
        boolean result = true;
        if (!StringUtil.startsWithInList(id + "", StringUtil.idsToList(ids))) {
            result = false;
        }
        return result;
    }

    /**
     * 判断Collection是否为空（包括null）.
     *
     * @param col Collection
     * @return boolean
     */
    public static boolean isEmpty(final Collection<?> col) {
        return col == null || col.isEmpty();
    }

    /**
     * 隐藏构造函数.
     */
    private StringUtil() {

    }

}
