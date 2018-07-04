package com.gxjtkyy.standardcloud.common.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ydlx on 2017/7/25.
 */
public class MapUtil {

    /**
     *  map转换成bean
     * @param clazz
     * @param map
     * @return
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static Object convertMapToBean(Class<?> clazz, Map<String, Object> map) throws IntrospectionException, IllegalAccessException, InstantiationException {
        //获取类属性
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        //创建对象
        Object obj = clazz.newInstance();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor descriptor : propertyDescriptors){
            String propertyName = descriptor.getName();
            if(map.containsKey(propertyName)){
                try {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(obj,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    continue;
                }
            }
        }
        return obj;
    }

    /***
     * 将bean转换成map
     * @param bean
     * @return
     */
    public static Map<String, Object> convertBeanToMap(Object bean){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if(bean== null) return  returnMap;
        try {
            Class<? extends Object> clazz = bean.getClass();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
                String propertyName = propertyDescriptor.getName();
                if(!propertyName.equals("class")){
                    Method method = propertyDescriptor.getReadMethod();
                    Object result = method.invoke(bean, new Object[0]);
                    if(result != null){
                        returnMap.put(propertyName, result);
                    }
                }
            }
        } catch (Exception e) {
            return null;
        }
        return returnMap;
    }
}
