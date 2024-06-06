package com.zongyu.java8.reflect;

public class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("MyThread come in");
    }
}
