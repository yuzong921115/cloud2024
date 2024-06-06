package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 归约
 * 收集
 */
public class StreamApi5Demo {
    /**
     * 收集
     */
    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 查找工资大于2W的
        List<Employee> e2= employees.stream()
                .filter(e->e.getSalary()>20000)
                .collect(Collectors.toList());
       e2.forEach(System.out::println);

        System.out.println("------------");
        Set<Employee> eset= employees.stream()
                .filter(e->e.getSalary()>20000)
                .collect(Collectors.toSet());
        eset.forEach(System.out::println);
    }

    /**
     * 归约
     */
    @Test
    public void test1() {
        // 1--10的自然数和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sumAge = list.stream()
                .reduce(0, Integer::sum);
        System.out.println(sumAge);

        System.out.println("-------------");

        // 计算员工工资之和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream()
                .map(Employee::getSalary);
        Optional<Double> sumMoney = salaryStream.reduce(Double::sum);
//        Optional<Double> sumMoney = salaryStream.reduce((d1, d2) -> d1 + d2);
        System.out.println(sumMoney);
    }
}
