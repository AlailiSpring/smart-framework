package org.smart4j.chapter3.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.chapter3.annotation.Action;
import org.smart4j.chapter3.bean.Handler;
import org.smart4j.chapter3.bean.Request;
import org.smart4j.chapter3.helper.ClassHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 控制器助手类
 * @Author: LiBaoDeng
 * @Date: 2018-09-03 17:59
 */
public final class ControllerHelper {

    /**
     * 存放请求与处理器映射关系
     */
    public static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static{
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtils.isNotEmpty(controllerClassSet)) {
            for (Class<?> controllerClass : controllerClassSet) {
                //获取controller中的所有方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtils.isNotEmpty(methods)) {
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getaHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
