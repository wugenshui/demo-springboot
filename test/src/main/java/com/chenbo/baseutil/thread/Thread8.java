package com.chenbo.baseutil.thread;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义 BlockingQueue
 *
 * @author : chenbo
 * @date : 2021-02-12
 */
public class Thread8 {

    private LinkedList<Object> list = new LinkedList<>();

    private AtomicInteger count = new AtomicInteger(0);

    private final int minSize = 0;

    private final int maxSize;

    private Object lock = new Object();

    Thread8(int size) {
        this.maxSize = size;
    }

    public void put(Object obj) {
        synchronized (lock) {
            while (this.count.get() >= this.maxSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(obj);
            count.incrementAndGet();
            System.out.println("新增元素" + obj);
            lock.notify();
        }
    }

    public Object take() {
        Object obj = null;
        synchronized (lock) {
            while (count.get() <= this.minSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            obj = list.removeFirst();
            count.decrementAndGet();
            System.out.println("-------------取出元素" + obj);
            lock.notify();
        }
        return obj;
    }

    public int getSize() {
        return count.get();
    }


    public static void main(String[] args) throws InterruptedException {
        Thread8 mq = new Thread8(5);
        mq.put("a");
        mq.put("b");
        mq.put("c");
        mq.put("d");
        mq.put("e");

        System.out.println("当前容器长度：" + mq.getSize());

        new Thread(() -> {
            mq.put("f");
            mq.put("g");
        }).start();

        new Thread(() -> {
            while (mq.getSize() > 0) {
                mq.take();
            }
        }).start();
    }
}
