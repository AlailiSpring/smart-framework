package org.smart4j.framework;

import org.smart4j.chapter3.helper.BeanHelper;
import org.smart4j.chapter3.helper.ClassHelper;
import org.smart4j.chapter3.helper.ControllerHelper;
import org.smart4j.chapter3.helper.IOCHelper;
import org.smart4j.chapter3.util.ClassUtil;

/**
 * @Description:
 * @Author: LiBaoDeng
 * @Date: 2018-09-05 00:09
 */
public final class HelperLoader {
    public static void init() {
        Class<?>[] classList = {ClassHelper.class, BeanHelper.class, IOCHelper.class, ControllerHelper.class};
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), false);
        }
    }

}
