package com.github.wugenshui.baseutil.baseutil.java.lang.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

/**
 * 通过
 *
 * @author : chenbo
 * @date : 2023-11-02
 */
class ThreadCreateTest {
    @Test
    void create() {
        Executors.newSingleThreadExecutor().submit(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }
}
