package com.github.wugenshui.baseutil.baseutil.thread;

/**
 * 单例模式，双重检查
 *
 * @author : chenbo
 * @date : 2021-02-14
 */
public class DubbleSingleton {

    private static DubbleSingleton ds;

    public static DubbleSingleton getInstance() {
        if (ds == null) {
            synchronized (DubbleSingleton.class) {
                if (ds == null) {
                    ds = new DubbleSingleton();
                }
            }
        }
        return ds;
    }
}
