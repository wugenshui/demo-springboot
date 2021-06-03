package com.chenbo.demo.mock.mockito.async;

import java.util.List;

/**
 * 回调接口
 */
public interface DummyCallback {
    public void onSuccess(List<String> result);

    public void onFail(int code);
}
