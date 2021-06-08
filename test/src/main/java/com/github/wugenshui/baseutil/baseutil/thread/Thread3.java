package com.github.wugenshui.baseutil.baseutil.thread;

/**
 * 脏读
 * 在读写方法上均加 synchronized
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread3 {
    private String username = "张三";

    private String password = "zhangSan";

    private synchronized void setValue(String username, String password) {
        this.username = username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password = password;
        System.out.println("setValue:username=" + username + " password=" + password);
    }

    public void getValue() {
        System.out.println("getValue:username=" + username + " password=" + password);
    }

    public static void main(String[] args) {
        Thread3 dirtyRead = new Thread3();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dirtyRead.setValue("李四", "liSi");
            }
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dirtyRead.getValue();
    }

}
