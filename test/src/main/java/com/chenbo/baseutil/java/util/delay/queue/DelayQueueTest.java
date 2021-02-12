package com.chenbo.baseutil.java.util.delay.queue;

import com.chenbo.baseutil.util.RandomUtil;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class DelayQueueTest extends Thread {

    public static DelayQueue<Item> queue = new DelayQueue<>();
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        System.out.println("开始时间:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    int millis = RandomUtil.getRandomNumber(100, 1500);
                    queue.put(new Item("延时消息：" + millis, 2, TimeUnit.SECONDS));
                    System.out.println("发送消息" + millis + " 当前时间：" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
                    Thread.sleep(millis);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    Item take = DelayQueueTest.queue.take();
                    System.out.format("收到消息:{%s} 当前时间:{%s}\n", take.name, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));
                }
            }
        }).start();

    }

}

class Item implements Delayed {
    /* 触发时间*/
    private long time;
    String name;

    public Item(String name, long time, TimeUnit unit) {
        this.name = name;
        this.time = System.currentTimeMillis() + (time > 0 ? unit.toMillis(time) : 0);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //return time - System.currentTimeMillis();
        return 0;
        //return unit.convert(2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Item item = (Item) o;
        long diff = this.time - item.time;
        // 改成>=会造成问题
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Item{" +
                "time=" + time +
                ", name='" + name + '\'' +
                '}';
    }
}