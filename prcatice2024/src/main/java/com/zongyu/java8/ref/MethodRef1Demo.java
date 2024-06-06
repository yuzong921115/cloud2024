package com.zongyu.java8.ref;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 * 1、使用情境：当要传递给lambda体的操作，已经由实现的方法了，可以使用方法引用
 * 2、方法引用，本质就是lambda表达式，而lambda表达式作为函数式接口的实例。
 * 所以，方法引用，也是函数式接口的实例
 * 3、使用格式：类或对象::方法名
 * 4、具体分为三种情况
 * 对象::非静态方法
 * 类::静态方法
 * 类::非静态方法
 * 5、方法引用使用的要求：
 * 要求接口中抽象方法的形参列表和返回值类型 与 方法引用的方法的形参列表和返回值类型相同
 */
public class MethodRef1Demo {
    /**
     * 3.3、Function中的R apply(T t)
     * Employee中的String getName()
     */
    @Test
    public void test33() {
        Employee employee = new Employee(1, "Tom", 23, 5000);
        Function<Employee, String> function = e -> e.getName();
        System.out.println(function.apply(employee));

        System.out.println("---------");

        Function<Employee, String> function2 = Employee::getName;
        System.out.println(function2.apply(employee));
    }

    /**
     * 3.2、BiPredicate中的boolean test(T t1,T t2)
     * String中的boolean t1.equals(t2)
     */
    @Test
    public void test32() {
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc", "abc"));

        System.out.println("---------");

        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("abc", "abcj"));
    }

    /**
     * 3.1、类::实例方法
     */
    @Test
    public void test3() {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "abd"));

        System.out.println("---------");

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abc", "abd"));
    }

    /**
     * 2.2、Function中的R apply(T t)
     * Math中的Long round(Double d)
     */
    @Test
    public void test22() {
        Function<Double, Long> function = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };
        System.out.println(function.apply(12.6));

        System.out.println("---------");

        Function<Double, Long> function1 = d -> Math.round(d);
        System.out.println(function1.apply(23.45));

        System.out.println("---------");

        Function<Double, Long> function2 = Math::round;
        System.out.println(function2.apply(3.6));
    }

    /**
     * 2.1、类::静态方法
     */
    @Test
    public void test2() {
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        System.out.println(com1.compare(12, 21));

        System.out.println("---------");

        Comparator<Integer> com2 = Integer::compare;
        System.out.println(com2.compare(40, 21));
    }

    /**
     * 1.2、Supplier中的T get()
     */
    @Test
    public void test12() {
        Employee employee = new Employee(1, "Tom", 23, 5000);
        Supplier<String> sup1 = () -> employee.getName();
        System.out.println(sup1.get());

        System.out.println("---------");

        Supplier<String> sup2 = employee::getName;
        System.out.println(sup2.get());
    }

    /**
     * 1.1、对象::实例方法
     */
    @Test
    public void test1() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("北京");

        System.out.println("---------");

        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("beijing");
    }
}
