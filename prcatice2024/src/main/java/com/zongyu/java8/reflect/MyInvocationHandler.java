package com.zongyu.java8.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object obj; // 需要使用被代理类的对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    /**
     * 当我们通过代理类的对象，调用方法a时，就会自动调用如下方法的invoke()
     * 将被代理类要执行的方法a的功能就声明在invoke()中
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HumanUtil util = new HumanUtil();
        util.method1();

        Object returnValue = method.invoke(obj, args);
        util.method2();

        return returnValue;
    }
}
