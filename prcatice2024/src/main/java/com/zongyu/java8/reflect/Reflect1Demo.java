package com.zongyu.java8.reflect;

import org.junit.Test;

public class Reflect1Demo {
    /**
     * 动态代理
     * 特点：
     * 动态代理是指客户通过代理类来调用其它对象的方法，并且在程序运行时根据需要创建目标类的代理对象
     * <p>
     * 问题：
     * 1、如何根据加载到内存中的被代理类，动态的创建一个代理类及其对象
     * 通过Proxy.newProxyInstance()实现;
     * 2、当通过代理类的对象调用方法a时，如何动态去调用被代理类中的同名方法a
     * 通过InvocationHandler接口的实现类及其方法invoke()
     */
    @Test
    public void test2() {

    }

    /**
     * 静态代理
     * 缺点：
     * 1、代理类和目标对象的类都是在编译期间确定下来，不利于程序的扩展
     * 2、每一个代理类只能为一个接口服务，这样一来程序开发中必然产生过多的代理
     */
    @Test
    public void test1() {
        MyThread t = new MyThread(); //相当于被代理类
        Thread thread = new Thread(t); //相当于代理类
        thread.start(); // 启动线程，调用线程的run方法
    }
}
