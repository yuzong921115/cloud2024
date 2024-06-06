package com.zongyu.java8.reflect;


import java.lang.reflect.Proxy;

public class ProxyFactory {
    /**
     * 调用此方法，返回一个代理类的对象，解决问题
     *
     * @param obj
     * @return
     */
    public static Object getProxyInstance(Object obj) { // 被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();

        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), handler);
    }
}
