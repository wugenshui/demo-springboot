package com.chenbo.baseutil.thread;

/**
 * 单例模式，静态内部类，多线程比较好的处理方式（推荐）
 *
 * @author : chenbo
 * @date : 2021-02-14
 */
public class InnerSingleton {
    private static class Singleton {
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance() {
        return Singleton.single;
    }
}
