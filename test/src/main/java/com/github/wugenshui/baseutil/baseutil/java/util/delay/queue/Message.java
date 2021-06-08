package com.github.wugenshui.baseutil.baseutil.java.util.delay.queue;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author : chenbo
 * @date : 2020-05-22
 */
public class Message implements Delayed {
    private String msg;
    private Date date;

    public Message(String msg, Date date) {
        this.msg = msg;
        this.date = date;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        //return unit.convert(TimeUnit.SECONDS.toMillis(1), TimeUnit.MILLISECONDS);
        return unit.convert(1, TimeUnit.SECONDS);
        //long delay = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(1) - System.currentTimeMillis();
        //return delay;
    }

    @Override
    public int compareTo(Delayed o) {
        Message message = (Message) o;
        long diff = this.date.getTime() - message.date.getTime();
        // 改成 >= 会造成问题
        if (diff <= 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", date=" + date +
                '}';
    }
}