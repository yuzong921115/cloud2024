package com.zongyu.juc.base.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * fork 拆分，大事化小
 * join 合并
 */
public class ThreadDemo19 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyTask对象
        MyTask myTask = new MyTask(0, 100);

        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);

        // 获取最终合并之后结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        // 关闭池对象
        forkJoinPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {
    // 拆分差值不超过10
    private static final Integer VALUE = 10;
    // 拆分开始值
    private int begin;
    // 拆分结束值
    private int end;
    // 返回结果
    private int result;

    // 创建有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * 拆分和合并过程
     *
     * @return
     */
    @Override
    protected Integer compute() {
        // 判断相加两个数值是否大于10
        if ((end - begin) < VALUE) {
            // 相加操作
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        } else {
            // 获取中间值
            int middle = (begin + end) / 2;
            // 拆分左边
            MyTask task01 = new MyTask(begin, middle);
            // 拆分右边
            MyTask task02 = new MyTask(middle + 1, end);

            task01.fork();
            task02.fork();

            // 合并结果
            result = task01.join() + task02.join();
        }
        return result;
    }
}
