package com.chenbo.baseutil.util;

import com.chenbo.baseutil.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author : chenbo
 * @date : 2019-12-08
 */
class FastJsonHelperTest {

    @Test
    public void serialize() {
        User user = new User(1, "张三");

        String jsonStr = FastJsonHelper.serialize(user);

        Assert.assertEquals("{\"id\":1,\"username\":\"张三\"}", jsonStr);
    }
}