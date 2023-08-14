/*
 * Copyright [2023] [msauth of copyright http://www.douclouds.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mszlu.auth.utils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author 码神之路
 */

public class JWTUtil {
    /**
     * 传入payload信息获取token
     * @param map payload @param secret
     * @return token
     */
    //JWT a.b.c
    public static String getToken(Map<String, String> map, String secret) {
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach(builder::withClaim);
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE, 7);
        //指定令牌的过期时间
        builder.withExpiresAt(instance.getTime());
        return builder.sign(Algorithm.HMAC256(secret));
    }

    /**
     * 验证token 合法性
     */
    public static DecodedJWT verify(String token, String secret) {
        //如果有任何验证异常，此处都会抛出异常
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

    /**
     * 获取token信息方法
     */
    public static Map<String, Claim> getTokenInfo(String token, String secret) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaims();
    }
}