package com.chenbo.baseutil.java.util;

/**
 * @author : chenbo
 * @date : 2020-06-02
 */
public class ThreadBuyTicket1 implements Runnable {

    private int ticketNumber = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNumber <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + ticketNumber-- + "张票");
        }
    }

    public static void main(String[] args) {
        ThreadBuyTicket1 threadBuyTicket1 = new ThreadBuyTicket1();

        // 多个线程操作同一资源的情况下，线程不安全，票数可能出现负数
        new Thread(threadBuyTicket1, "张三").start();
        new Thread(threadBuyTicket1, "李四").start();
        new Thread(threadBuyTicket1, "黄牛").start();
    }
}
