package com.zongyu.java8.optional;

import org.junit.Test;

import java.util.Optional;

public class Optional1Demo {
    @Test
    public void test3() {
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("苍老师"));
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }

    public String getGirlName2(Boy boy) {
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        Boy boy1 = optionalBoy.orElse(new Boy(new Girl("迪丽热巴")));

        Girl girl = boy1.getGirl();
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("古力娜扎"));
        return girl1.getName();
    }

    @Test
    public void test2() {
        Boy boy = new Boy();
//        boy = null;
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    public String getGirlName(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    /**
     * 创建对象的方式
     */
    @Test
    public void test1() {
        // Optional.of(T t):创建一个Optional实例，t必须非空
        Girl girl = new Girl();
//        girl =null; // 会报错
        Optional<Girl> optionalGirl = Optional.of(girl);

        // Optional.empty():创建一个空的Optional实例


        // Optional.ofNullable:t可以为null
        Girl girl2 = new Girl();
//        girl2 = null; //正常打印：Optional.empty
        Optional<Girl> optionalGirl2 = Optional.ofNullable(girl2);
        // orElse 如果当前的Optional内部封装的t非空，则返回内部的t
        Girl girl1 = optionalGirl2.orElse(new Girl("舒淇"));
        System.out.println(girl1);
    }
}
