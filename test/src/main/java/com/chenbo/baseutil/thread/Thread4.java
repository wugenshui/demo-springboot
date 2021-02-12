package com.chenbo.baseutil.thread;

/**
 * 变量在线程间不共享，增加 volatile
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread4 extends Thread {

    private boolean isRunning = true;

    private void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public void run() {
        System.out.println("线程启动");
        while (isRunning) {

        }
        System.out.println("线程停止");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread4 runThread = new Thread4();
        runThread.start();
        Thread.sleep(3000);
        runThread.setRunning(false);
        System.out.println("isRunning的值被设置为false");
        Thread.sleep(1000);
        System.out.println("isRunning = " + runThread.isRunning);
    }
}
