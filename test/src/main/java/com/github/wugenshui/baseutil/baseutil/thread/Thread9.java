package com.github.wugenshui.baseutil.baseutil.thread;

/**
 * ThreadLocal demo
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread9 {

    public static ThreadLocal<String> th = new ThreadLocal<>();

    public void setTh(String value) {
        th.set(value);
    }

    public void getTh() {
        System.out.println(Thread.currentThread().getName() + ":" + th.get());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread9 tl = new Thread9();

        new Thread(() -> {
            tl.setTh("张三");
            tl.getTh();
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                tl.getTh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
