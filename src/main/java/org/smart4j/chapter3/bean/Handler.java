package org.smart4j.chapter3.bean;

import java.lang.reflect.Method;

/**
 * @Description: 处理类,封装处理Controller和方法
 * @Author: LiBaoDeng
 * @Date: 2018-09-03 17:55
 */
public class Handler {

    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
