package com.zongyu.java8.lam;

import org.junit.Test;

import java.util.Comparator;

/**
 * lambda表达式的使用
 * <p>
 * 1、举例：(o1, o2) -> Integer.compare(o1, o2);
 * 2、格式：
 * -> :lambda操作符 或 箭头操作符
 * -> 左边: lambda形参列表（其实就是接口中的抽象方法的形参列表）
 * -> 右边: lambda体（其实就是重写的抽象方法的方法体）
 * 3、lambda表达式的使用：（分为6种情况介绍） PS:练习在Lambda2Demo.java
 * 4、lambda表达式的本质：作为接口的实例
 * 5、如果一个接口中，只声明了一个抽象方法，则此方法就成为函数式接口。
 * 我们可以在接口上使用注解@FunctionalInterface来检查是否是一个函数式接口
 * 6、所以以前用匿名实现类表示的现在可以用lambda表达式来写。
 */
public class Lambda1Demo {
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("----------");

        Runnable r2 = () -> System.out.println("我爱北京故宫");
        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = com1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("------------");

        // lambda表达式
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare2 = com2.compare(32, 31);
        System.out.println(compare2);

        System.out.println("------------");

        // 方法引用
        Comparator<Integer> com3 = Integer::compare;
        int compare3 = com3.compare(2, 2);
        System.out.println(compare3);
    }
}
