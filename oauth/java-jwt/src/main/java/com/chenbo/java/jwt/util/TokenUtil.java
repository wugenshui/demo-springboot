package com.chenbo.java.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.chenbo.java.jwt.entity.User;

/**
 * @author : chenbo
 * @date : 2020-03-31
 */
public class TokenUtil {
    public static String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
