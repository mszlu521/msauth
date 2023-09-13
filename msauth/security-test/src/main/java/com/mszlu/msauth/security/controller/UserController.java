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
package com.mszlu.msauth.security.controller;

import com.mszlu.msauth.utils.JWTUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 码神之路
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/login")
    public String login(String username,String password){
        if (username.equals("mszlu") && password.equals("mszlu123456")){
            Map<String, String> map = new HashMap<>();
            map.put("username",username);
            return JWTUtil.getToken(map,"123456");
        }
        return "用户密码不正确";
    }

    @GetMapping("getUserInfo")
    public String getUserInfo(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }
}
