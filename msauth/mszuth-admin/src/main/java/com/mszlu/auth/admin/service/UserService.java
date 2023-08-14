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

import com.mszlu.auth.admin.model.vo.UserInfoVO;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 码神之路
 */
@Service
public class UserService {

    public UserInfoVO getUserLoginInfo() {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(1000L);
        userInfoVO.setNickname("码神之路");
        userInfoVO.setAvatar("https://img2.woyaogexing.com/2023/08/11/4ed63711c631465df3ec5235d51504d5.jpg");
        Set<String> roles = new HashSet<>();
        roles.add("ROOT");
        roles.add("ADMIN");
        userInfoVO.setRoles(roles);
        Set<String> perms = new HashSet<>();
        userInfoVO.setPerms(perms);
        return userInfoVO;
    }
}
