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

import com.mszlu.msauth.admin.model.dto.AppDTO;
import com.mszlu.msauth.admin.model.dto.UserDTO;
import com.mszlu.msauth.admin.model.dto.UserForm;
import com.mszlu.msauth.admin.model.vo.AppVO;
import com.mszlu.msauth.admin.model.vo.UserInfoVO;
import com.mszlu.msauth.admin.model.vo.UserVO;
import com.mszlu.msauth.admin.service.AppService;
import com.mszlu.msauth.admin.service.UserService;
import com.mszlu.msauth.response.PageResult;
import com.mszlu.msauth.response.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 码神之路
 */
@RestController
@RequestMapping("/api/v1/app")
@RequiredArgsConstructor
public class AppController {

    private final AppService appService;

    @PostMapping("/page")
    public Result<PageResult<AppVO>> page(@RequestBody AppDTO appDTO) {
        appDTO.fillPage();
        return Result.success(appService.findAppPage(appDTO));
    }
}
