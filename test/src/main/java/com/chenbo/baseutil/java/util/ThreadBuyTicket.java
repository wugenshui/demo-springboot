package com.chenbo.baseutil.java.util;

/**
 * @author : chenbo
 * @date : 2020-06-02
 */
public class ThreadBuyTicket implements Runnable {

    private int ticketNumber = 10;

    @Override
    public void run() {
        while (true) {
            if (buyTicket()) {
                break;
            }
        }
    }

    // synchronized 同步方法
    private synchronized boolean buyTicket() {
        if (ticketNumber <= 0) {
            return true;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNumber-- + "张票");
        return false;
    }

    public static void main(String[] args) {
        ThreadBuyTicket threadBuyTicket = new ThreadBuyTicket();

        // 多个线程操作同一资源的情况下，线程不安全，票数可能出现负数
        new Thread(threadBuyTicket, "张三").start();
        new Thread(threadBuyTicket, "李四").start();
        new Thread(threadBuyTicket, "黄牛").start();
    }
}
