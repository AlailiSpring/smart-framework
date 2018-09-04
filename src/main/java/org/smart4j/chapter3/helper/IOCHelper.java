package org.smart4j.chapter3.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.chapter3.annotation.Inject;
import org.smart4j.chapter3.helper.BeanHelper;
import org.smart4j.chapter3.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

/**
 * @Description:依赖注入助手类
 * 实现控制反转的步骤：
 * 1、BeanHelper获取所有的BeanMap，BeanMap记录了类与对象的映射关系
 * 2、遍历BeanMap，取出Bean类与Bean实例
 * 3、BeanClass通过反射获取所有成员变量，遍历成员变量，再循环中判断是否有Inject注解，如果有，那么从BeanMap中取出Bean实例
 * 4、Bean实例通过反射工具类setField方法修改当前成员变量的值
 * @Author: LiBaoDeng
 * @Date: 2018-09-01 18:56
 */
public final class IOCHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtils.isNotEmpty((Collection<?>) beanMap)){
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
