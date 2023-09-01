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
package com.mszlu.msauth.admin.service;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mszlu.msauth.admin.model.convert.AppConvert;
import com.mszlu.msauth.admin.model.convert.UserConvert;
import com.mszlu.msauth.admin.model.dto.AppDTO;
import com.mszlu.msauth.admin.model.dto.UserDTO;
import com.mszlu.msauth.admin.model.dto.UserForm;
import com.mszlu.msauth.admin.model.entity.App;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.entity.User;
import com.mszlu.msauth.admin.model.entity.UserOrg;
import com.mszlu.msauth.admin.model.enums.AuthType;
import com.mszlu.msauth.admin.model.vo.AppVO;
import com.mszlu.msauth.admin.model.vo.UserInfoVO;
import com.mszlu.msauth.admin.model.vo.UserVO;
import com.mszlu.msauth.admin.repository.AppRepository;
import com.mszlu.msauth.admin.repository.OrganizationRepository;
import com.mszlu.msauth.admin.repository.UserOrgRepository;
import com.mszlu.msauth.admin.repository.UserRepository;
import com.mszlu.msauth.response.PageResult;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 码神之路
 */
@Service
@RequiredArgsConstructor
public class AppService extends ServiceImpl<AppRepository, App> {

    private final AppConvert appConvert;

    public PageResult<AppVO> findAppPage(AppDTO appDTO) {
        LambdaQueryWrapper<App> queryWrapper = Wrappers.lambdaQuery();
        if (CharSequenceUtil.isNotBlank(appDTO.getKeywords())){
            queryWrapper.like(App::getName,appDTO.getKeywords());
        }
        Page<App> appPage = new Page<>(appDTO.getPageNum(),appDTO.getPageSize());
        this.page(appPage, queryWrapper);
        List<AppVO> appVOList = appPage.getRecords().stream().map(app -> {
            AppVO appVO = appConvert.toVO(app);
            appVO.setAuthType(AuthType.codeOf(app.getAuthType()));
            return appVO;
        }).toList();
        return PageResult.of(appVOList, (int) appPage.getTotal(),appDTO.getPageNum(),appDTO.getPageSize());
    }
}
