package com.fjm.soft.common.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 对bean操作的工具库.
 *
 * @Author: fongjinming
 * @CreateTime: 2019-08-19 19:17
 * @Description:
 */
public class BeanUtils {
    /**
     * 动态对bean的field赋值（指定类型，方便递归调用）.
     *
     * @param clazz 指定用某种类作处理
     * @param bean  要处理的对象
     * @param name  field名
     * @param value 要设置的值
     * @return 成功设置返回true，否则返回false
     */
    private static boolean setField(final Class<?> clazz, final Object bean,
                                    final String name, final Object value) {
        try {
            Field field = clazz.getDeclaredField(name);

            if (!field.isAccessible()) {
                field.setAccessible(true);
            }

            field.set(bean, value);

            return true;
        } catch (Exception e) {
            Class<?> parent = clazz.getSuperclass();

            if (parent != null && parent != Object.class) {
                return setField(parent, bean, name, value);
            } else {
                return false;
            }
        }
    }

    /**
     * 动态对bean的field赋值（过滤掉Exception的抛出，改用返回boolean表示是否正确赋值）.
     *
     * @param bean  要处理的对象
     * @param name  field名
     * @param value 要设置的值
     * @return 成功设置返回true，否则返回false
     */
    private static boolean setField(final Object bean, final String name,
                                    final Object value) {
        return setField(bean.getClass(), bean, name, value);
    }

    /**
     * 动态对bean的属性赋值（过滤掉Exception的抛出，改用返回boolean表示是否正确赋值）.
     *
     * @param bean  要处理的对象
     * @param name  属性名
     * @param value 要设置的属性值
     * @return 成功设置返回true，否则返回false
     */
    public static boolean setProperty(final Object bean, final String name,
                                      final Object value) {
        try {
            PropertyUtils.setProperty(bean, name, value);

            return true;
        } catch (NoSuchMethodException e) {
            return setField(bean, name, value);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对象转map.
     *
     * @param obj 对象
     * @return map
     * @throws Exception 异常
     */
    public static HashMap<String, Object> convertToMap(final Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null) {
                map.put(varName.toUpperCase(), o.toString());
            }
            fields[i].setAccessible(accessFlag);
        }
        return map;
    }

    /**
     * 隐藏构造函数.
     */
    private BeanUtils() {
    }
}
