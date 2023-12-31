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
package com.mszlu.msauth.admin.controller;

import com.mszlu.msauth.admin.model.dto.LoginDTO;
import com.mszlu.msauth.admin.model.dto.LoginResult;
import com.mszlu.msauth.response.Result;
import com.mszlu.msauth.utils.JWTUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 码神之路
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @PostMapping("/login")
    public Result<LoginResult> login(@RequestBody LoginDTO loginDTO) {
        // 生成token
        Map<String,String> map = new HashMap<>(1);
        map.put("username",loginDTO.getUsername());
        String accessToken = JWTUtil.getToken(map,"123456");
        LoginResult loginResult = LoginResult.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();
        return Result.success(loginResult);
    }
}
