package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 */
public class StreamApi2Demo {
    @Test
    public void test3() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list3 = new ArrayList();
        list3.add(4);
        list3.add(5);
        list3.add(6);

//        list1.add(list3);
//        System.out.println(list1);

        list1.addAll(list3);
        System.out.println(list1);
    }

    /**
     * 映射
     */
    @Test
    public void test2() {
        System.out.println("---------转大写");
        List<String> list = Arrays.asList("ac", "be", "cf");
        list.stream()
                .map(str -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("---------名字长度大于3的");

        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> nameStream = employees.stream()
                .map(Employee::getName);

        nameStream.filter(name -> name.length() > 3)
                .forEach(System.out::println);

        System.out.println("---------传统");
        Stream<Stream<Character>> streamStream = list.stream()
                .map(StreamApi2Demo::fromStringToStream);
        streamStream.forEach(s->{
            s.forEach(System.out::println);
        });

        System.out.println("---------flatMap");
        Stream<Character> streamStream2 = list.stream()
                .flatMap(StreamApi2Demo::fromStringToStream);
        streamStream2.forEach(System.out::println);
    }

    /**
     * 将字符串中的多个字符构成的集合转换为对应的Stream的实例
     *
     * @param str
     * @return
     */
    public static Stream<Character> fromStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();

        // filter(Predicate p)——接收Lambda，从流中排除某些元素
        Stream<Employee> stream = list.stream();
        stream.filter(e -> e.getSalary() > 15000)
                .forEach(System.out::println);

        System.out.println("---------");

        // limit(n)——截断流，使其元素不超过给定数量
        list.stream()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("---------");

        // skip(n)——跳过元素，返回一个扔掉了n个元素的流，若流中元素不足4个，则返回一个空流
        list.stream()
                .skip(3)
                .forEach(System.out::println);

        System.out.println("---------");

        // distinct()——筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        list.stream()
                .distinct()
                .forEach(System.out::println);
    }
}
