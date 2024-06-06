package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 */
public class StreamApi4Demo {
    /**
     * 匹配与查找2
     */
    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

        System.out.println("-----------6、返回流中总个数");
        // 6、返回流中总个数
        long count = employees.stream()
                .filter(e -> e.getSalary() > 20000)
                .count();
        System.out.println(count);

        System.out.println("-----------");
        System.out.println("-----------6、返回流中最大值");
        // 6、返回流中最大值
        Stream<Double> salaryStream = employees.stream()
                .map(e -> e.getSalary());
        Optional<Double> maxSalary = salaryStream.max(Double::compare);
        System.out.println(maxSalary);

        System.out.println("-----------");
        System.out.println("-----------7、返回流中最小值");
        // 7、返回流中最小值
        Optional<Employee> em = employees.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(em);

        System.out.println("-----------");
        System.out.println("-----------8、内部迭代");
        // 8、内部迭代
        employees.stream()
                .forEach(System.out::println);

        System.out.println("-----------");
        System.out.println("-----------使用集合的遍历操作");
        // 使用集合的遍历操作
        employees.forEach(System.out::println);
    }

    /**
     * 匹配与查找1
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        System.out.println("1、判断所有的员工年龄都大于18岁");
        // 1、判断所有的员工年龄都大于18岁
        boolean allMatch = employees.stream()
                .allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        System.out.println("-----------");
        System.out.println("-----------2、判断员工工资是否大于1W");
        // 2、判断员工工资是否大于1W
        boolean anyMatch = employees.stream()
                .anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        System.out.println("-----------");
        System.out.println("-----------3、判断员工姓:雷，没有匹配的时候返回true");
        // 3、判断员工姓:雷，没有匹配的时候返回true
        boolean flag = employees.stream()
                .noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(flag);

        System.out.println("-----------");
        System.out.println("-----------4、查找第一个元素");
        // 4、查找第一个元素
        Optional<Employee> employee = employees.stream()
                .findFirst();
        System.out.println(employee);

        System.out.println("-----------");
        System.out.println("-----------5、返回任意元素");
        // 5、返回任意元素
        Optional<Employee> employee2 = employees.stream()
                .findAny();
        System.out.println(employee2);
    }
}
