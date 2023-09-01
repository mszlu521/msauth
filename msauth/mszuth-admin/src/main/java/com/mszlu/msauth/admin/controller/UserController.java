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

import com.mszlu.msauth.admin.model.dto.UserDTO;
import com.mszlu.msauth.admin.model.dto.UserForm;
import com.mszlu.msauth.admin.model.vo.UserInfoVO;
import com.mszlu.msauth.admin.model.vo.UserVO;
import com.mszlu.msauth.admin.service.UserService;
import com.mszlu.msauth.response.PageResult;
import com.mszlu.msauth.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码神之路
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/me")
    public Result<UserInfoVO> getUserLoginInfo() {
        UserInfoVO userInfoVO = userService.getUserLoginInfo();
        return Result.success(userInfoVO);
    }

    @PostMapping("/page")
    public Result<PageResult<UserVO>> page(@RequestBody UserDTO userDTO) {
        userDTO.fillPage();
        return Result.success(userService.findUserPage(userDTO));
    }
    @PostMapping("/add")
    public Result<Long> add(@RequestBody UserForm userForm) {
        return Result.success(userService.add(userForm));
    }
    @PostMapping("{id}")
    public Result<UserForm> detail(@PathVariable Long id) {
        return Result.success(userService.findUserById(id));
    }
    @PostMapping("/{id}/update")
    public Result<Long> update(@PathVariable Long id, @RequestBody UserForm userForm) {
        return Result.success(userService.modifyUserById(id, userForm));
    }
}
