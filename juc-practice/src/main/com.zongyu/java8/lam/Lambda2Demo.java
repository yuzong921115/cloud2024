package com.zongyu.java8.lam;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * lambda表达式的使用：（分为6种情况介绍）
 * 1、无参，无返回值
 * 2、需要一个参数，但没有返回值
 * 3、数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
 * 4、lambda若只需要一个参数时，参数的小括号可以省略
 * 5、lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
 * 6、当lambda体只有一条语句时，return与大括号多有，都可以省略
 * <p>
 * 总结：
 * -> 左边: 形参列表参数类型可以省略；如果只有一个参数，则()也可以省略
 * -> 右边:使用{}包裹，如果一条执行语句（可能是return），则可以省略{}和return
 */
public class Lambda2Demo {
    @Test
    public void test7() {
        Consumer<String> con2 = (s) -> System.out.println(s);
        con2.accept("2一个是听的人当真了，一个是说的人当真的。");
    }

    /**
     * 6、当lambda体只有一条语句时，return与大括号多有，都可以省略
     */
    @Test
    public void test6() {
        Comparator<Integer> com1 = (o1, o2) -> {
            return o1.compareTo(o2);
        };
        int compare1 = com1.compare(12, 5);
        System.out.println(compare1);

        System.out.println("----------");

        Comparator<Integer> com2 = (o1, o2) -> o1.compareTo(o2);
        int compare2 = com2.compare(12, 17);
        System.out.println(compare2);
    }

    /**
     * 5、lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
     */
    @Test
    public void test5() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        int compare1 = com1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("----------");

        Comparator<Integer> com2 = (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare2 = com2.compare(12, 5);
        System.out.println(compare2);
    }

    /**
     * 4、lambda若只需要一个参数时，参数的小括号可以省略
     */
    @Test
    public void test4() {
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("1一个是听的人当真了，一个是说的人当真的。");

        System.out.println("----------");

        Consumer<String> con2 = s -> {
            System.out.println(s);
        };
        con2.accept("2一个是听的人当真了，一个是说的人当真的。");
    }

    /**
     * 3、数据类型可以省略，因为可由编译器推断得出，称为“类型推断”
     */
    @Test
    public void test3() {
        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("1一个是听的人当真了，一个是说的人当真的。");

        System.out.println("----------");

        Consumer<String> con2 = (s) -> {
            System.out.println(s);
        };
        con2.accept("2一个是听的人当真了，一个是说的人当真的。");
    }

    /**
     * 2、需要一个参数，但没有返回值
     */
    @Test
    public void test2() {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("谎言和誓言的区别是什么？");

        System.out.println("----------");

        Consumer<String> con1 = (String s) -> {
            System.out.println(s);
        };
        con1.accept("一个是听的人当真了，一个是说的人当真的。");
    }

    /**
     * 1、无参，无返回值
     */
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
}
