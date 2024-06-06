package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1、Stream关注的是数据的运算，与CPU打交道
 * 集合关注的是数据的存储，与内存打交道
 * <p>
 * 2、
 * Stream 自己不会存储元素
 * Stream 不会改变源对象，相反，他们会返回一个持有结果的新的Stream
 * Stream 操作是延迟执行的，意味着他们会等需要结果的时候才执行
 * <p>
 * 3、Stream执行流程
 * Stream的实例化
 * 一系列的中奖操作（过滤，映射等）
 * 终止操作
 * <p>
 * 4、说明
 * 一个中间操作链，对数据源的数据进行处理
 * 一旦执行终止操作，就执行中间操作链，并产生结果，之后，不会再被使用
 */
public class StreamApi1Demo {
    /**
     * 创建Stream方式4：创建无限流 PS:了解即可，用的很少
     */
    @Test
    public void test4() {
        // 迭代
        // public static<T> Stream<T> iterate(final T seed,final UnaryOperator<T> f)
        // 遍历前10个偶数
        Stream.iterate(0, t -> t + 2)
                .limit(10)
                .forEach(System.out::println);

        // 生成
        // public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * 创建Stream方式3：通过Stream的of()
     */
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4);
    }

    /**
     * 创建Stream方式2：通过数组
     */
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4};
        // 调用Arrays类的static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);

        Employee e1 = new Employee(1001, "马云", 34, 6000);
        Employee e2 = new Employee(1002, "刘强东", 40, 216000);
        Employee[] arr1 = new Employee[]{e1, e2};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }

    /**
     * 创建Stream方式1：通过集合
     */
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

        // 1. default Stream<E> stream(): 返回一个顺序流
        Stream<Employee> stream = employees.stream();

        // 2. default Stream<E> parallelStream(): 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }
}
