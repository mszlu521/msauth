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
package com.mszlu.auth.admin.service;

import com.mszlu.auth.admin.model.dto.LoginDTO;
import com.mszlu.auth.admin.model.dto.LoginResult;
import com.mszlu.auth.utils.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 码神之路
 */
@Service
public class AuthService {


    public LoginResult login(LoginDTO loginDTO) {
        Map<String,String> map = new HashMap<>(1);
        map.put("username",loginDTO.getUsername());
        String accessToken = JWTUtil.getToken(map,"123456");
        return LoginResult.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();
    }
}
