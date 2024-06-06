package com.zongyu.juc.base.lock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 8种锁
 * synchronized 锁当前对象
 * static synchronized 锁当前Class
 */
public class ThreadDemo10 {
    /**
     * 标准访问，打印内容
     * ----------sendSMS
     * ----------sendEmail
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        Phone p1 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 停4秒在短信方法内，打印内容
     * ----------sendSMS
     * ----------sendEmail
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main2(String[] args) throws InterruptedException {
        Phone p1 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMS4();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 新增普通的hello方法，打印内容
     * ----------getHello
     * ----------sendSMS
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main3(String[] args) throws InterruptedException {
        Phone p1 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMS4();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p1.getHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 现有两部手机，打印内容
     * ----------sendEmail
     * ----------sendSMS
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main4(String[] args) throws InterruptedException {
        Phone p1 = new Phone();
        Phone p2 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMS4();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 两个静态同步方法，1部手机，打印内容
     * ----------sendSMSStatic
     * ----------sendEmailStatic
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main5(String[] args) throws InterruptedException {
        Phone p1 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMSStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p1.sendEmailStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 两个静态同步方法，2部手机，打印内容
     * ----------sendSMSStatic
     * ----------sendEmailStatic
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main6(String[] args) throws InterruptedException {
        Phone p1 = new Phone();
        Phone p2 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMSStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p2.sendEmailStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 1个静态同步方法，1个普通同步方法，1部手机，打印内容
     * ----------sendEmail
     * ----------sendSMSStatic
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main7(String[] args) throws InterruptedException {
        Phone p1 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMSStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p1.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

    /**
     * 1个静态同步方法，1个普通同步方法，2部手机，打印内容
     * ----------sendEmail
     * ----------sendSMSStatic
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main8(String[] args) throws InterruptedException {
        Phone p1 = new Phone();
        Phone p2 = new Phone();

        new Thread(() -> {
            try {
                p1.sendSMSStatic();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        // 等待100毫秒
        Thread.sleep(100);

        new Thread(() -> {
            try {
                p2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}

class Phone {
    /**
     * 锁的当前Class
     *
     * @throws Exception
     */
    public static synchronized void sendSMSStatic() throws Exception {
        // 停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("----------sendSMSStatic");
    }


    public static synchronized void sendEmailStatic() throws Exception {
        System.out.println("----------sendEmailStatic");
    }

    /**
     * synchronized 锁的当前对象
     *
     * @throws Exception
     */
    public synchronized void sendSMS4() throws Exception {
        // 停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("----------sendSMS");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("----------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("----------sendEmail");
    }

    public void getHello() {
        System.out.println("----------getHello");
    }
}