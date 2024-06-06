package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 排序
 */
public class StreamApi3Demo {
    @Test
    public void test1() {
        // sorted()——自然排序
        List<Integer> list = Arrays.asList(12, 4, 5, 1, 88, 100);
        list.stream()
                .sorted()
                .forEach(System.out::println);

        // 会报错 原因：Employee没有实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream()
//                .sorted()
//                .forEach(System.out::println);

        // sorted(Comparator com)——定制排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream()
                .sorted((e1, e2) -> {
                    return Integer.compare(e1.getAge(), e2.getAge());
                }).forEach(System.out::println);

        System.out.println("-------------");

        employees.stream()
                .sorted((e1, e2) -> {
                    int ageValue = Integer.compare(e1.getAge(), e2.getAge());
                    if (ageValue != 0) {
                        return ageValue;
                    } else {
                        // 从大到小 return -Double.compare(e1.getSalary(),e2.getSalary());
                        return Double.compare(e1.getSalary(), e2.getSalary());
                    }
                }).forEach(System.out::println);
    }
}
