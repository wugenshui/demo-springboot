package com.chenbo.baseutil.java.util;

import com.chenbo.baseutil.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author : chenbo
 * @date : 2020-08-10
 */
@SpringBootTest
public class OptionalTest {
    @Test(expected = NoSuchElementException.class)
    public void emptyGetTest() {
        Optional<User> emptyOpt = Optional.empty();
        // 空值 get 会抛异常
        emptyOpt.get();
    }

    @Test(expected = NullPointerException.class)
    public void optionalOfTest() {
        // of 参数为 null 会抛异常
        Optional.of(null);
    }

    @Test
    public void ifPresentTest() {
        User user = new User();
        user.setId(10);
        user.setUsername("foo");

        Optional<User> opt = Optional.ofNullable(user);
        Assert.assertTrue(opt.isPresent());

        Assert.assertEquals(user.getId(), opt.get().getId());


        opt.ifPresent(u ->
                {
                    System.out.println("username = " + u.getUsername());
                    Assert.assertEquals(user.getUsername(), u.getUsername());
                }
        );
    }

    @Test
    public void defaultTest() {
        User user1 = null;
        User user2 = new User();
        user2.setId(10);
        user2.setUsername("foo");

        User result = Optional.ofNullable(user1).orElse(user2);
        Assert.assertEquals(user2.getId(), result.getId());
        Assert.assertEquals(user2.getUsername(), result.getUsername());
    }
}
