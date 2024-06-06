package com.zongyu.java8.func;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java内置的4大核心函数式接口
 * Consumer<T> 消费型接口
 * 参数类型 T
 * 返回类型void
 * 对类型为T的对象应用操作，包含方法：void accept(T t)
 * <p>
 * Supplier<T> 供给型接口
 * 参数类型 无
 * 返回类型 T
 * 返回类型为T的对象，包含方法：T get()
 * <p>
 * Function<T,R> 函数型接口
 * 参数类型 T
 * 返回类型 R
 * 对类型为T的对象应用操作，并返回结果。结果是R类型的对象，包含方法：R apply(T t)
 * <p>
 * Predicate<T> 断定型接口
 * 参数类型 T
 * 返回类型 boolean
 * 确定类型为T的对象是否满足某约束，并返回boolean值，包含方法：boolean test(T t)
 */
public class Function1Demo {
    @Test
    public void test2() {
        List<String> list = Arrays.asList("北京", "上海", "天津", "东京", "普京");

        List<String> listStr = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(listStr);

        System.out.println("--------------");

        List<String> listStr2 = filterString(list, s -> s.contains("京"));
        System.out.println(listStr2);
    }

    /**
     * 根据给定的规则，过滤集合中的字符串，此规则由Predicate的方法决定
     *
     * @param list
     * @param pre
     * @return
     */
    public ArrayList<String> filterString(List<String> list, Predicate<String> pre) {
        ArrayList<String> filterList = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }

        return filterList;
    }

    @Test
    public void test1() {
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累，买瓶水：" + aDouble);
            }
        });

        System.out.println("--------------");

        happyTime(400, money -> System.out.println("学习太累，买瓶水：" + money));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }
}
