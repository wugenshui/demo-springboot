package com.chenbo.demo.mock.mockito.async;

import java.util.Collections;

/**
 * 真正的异步执行操作
 */
public class DummyCollaborator {

    public static int ERROR_CODE = 1;

    public DummyCollaborator() {
        // empty
    }

    public void doSomethingAsynchronously(final DummyCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("doSomethingAsynchronously start");
                    Thread.sleep(5000);
                    System.out.println("doSomethingAsynchronously start after 5s");
                    callback.onSuccess(Collections.EMPTY_LIST);
                    System.out.println("doSomethingAsynchronously call onSuccess");
                } catch (InterruptedException e) {
                    callback.onFail(ERROR_CODE);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
