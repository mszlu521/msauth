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
import com.mszlu.msauth.admin.model.convert.UserConvert;
import com.mszlu.msauth.admin.model.dto.UserDTO;
import com.mszlu.msauth.admin.model.dto.UserForm;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.entity.User;
import com.mszlu.msauth.admin.model.entity.UserOrg;
import com.mszlu.msauth.admin.model.vo.UserInfoVO;
import com.mszlu.msauth.admin.model.vo.UserVO;
import com.mszlu.msauth.admin.repository.OrganizationRepository;
import com.mszlu.msauth.admin.repository.UserOrgRepository;
import com.mszlu.msauth.admin.repository.UserRepository;
import com.mszlu.msauth.response.PageResult;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
public class UserService extends ServiceImpl<UserRepository, User> {

    private final UserConvert userConvert;

    @Resource
    private UserOrgRepository userOrgRepository;
    @Resource
    private OrganizationRepository organizationRepository;

    public UserInfoVO getUserLoginInfo() {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(1000L);
        userInfoVO.setNickname("码神之路");
        userInfoVO.setAvatar("https://img2.woyaogexing.com/2023/08/11/4ed63711c631465df3ec5235d51504d5.jpg");
        Set<String> roles = new HashSet<>();
        roles.add("ROOT");
        userInfoVO.setRoles(roles);
        Set<String> perms = new HashSet<>();
        userInfoVO.setPerms(perms);
        return userInfoVO;
    }

    public PageResult<UserVO> findUserPage(UserDTO userDTO) {
        //user中包含 组织 返回组织名称
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        Page<User> userPage = new Page<>(userDTO.getPageNum(),userDTO.getPageSize());
        if (CharSequenceUtil.isNotBlank(userDTO.getKeywords())){
            queryWrapper.
                    like(User::getName,userDTO.getKeywords()).
                    or().
                    like(User::getMobile,userDTO.getKeywords());
        }
        if (userDTO.getStatus() != null){
            queryWrapper.eq(User::getStatus,userDTO.getStatus());
        }
        Page<User> page = this.page(userPage, queryWrapper);
        List<User> records = page.getRecords();
        if (records.isEmpty()){
            return PageResult.empty();
        }
        //用户关联的组织列表
        List<Long> userIdList = records.stream().map(User::getId).toList();
        LambdaQueryWrapper<UserOrg> userOrgQuery = Wrappers.lambdaQuery();
        userOrgQuery.in(UserOrg::getUserId, userIdList);
        List<UserOrg> userOrgList = userOrgRepository.selectList(userOrgQuery);
        List<Long> orgIdList = userOrgList.stream().map(UserOrg::getOrgId).toList();
        //userOrgList userId - List<orgId>
        Map<Long, List<Long>> userOrgMap = userOrgList.stream().collect(Collectors.groupingBy(UserOrg::getUserId, Collectors.mapping(UserOrg::getOrgId, Collectors.toList())));
        //根据组织id 查询组织信息
        LambdaQueryWrapper<Organization> orgQuery = Wrappers.lambdaQuery();
        orgQuery.in(Organization::getId,orgIdList);
        List<Organization> organizationList = organizationRepository.selectList(orgQuery);
        //组合数据
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : records) {
            List<Organization> userOrganizationList = ListUtils.select(organizationList, o -> ListUtils.emptyIfNull(userOrgMap.get(user.getId())).contains(o.getId()));
            UserVO userVO = userConvert.toVO(user);
            List<String> nameList = userOrganizationList.stream().map(Organization::getName).toList();
            userVO.setOrgNames(nameList);
            userVOList.add(userVO);
        }
        return PageResult.of(userVOList, (int) page.getTotal(),userDTO.getPageNum(),userDTO.getPageSize());
    }

    @Transactional
    public Long add(UserForm userForm) {
        User user = userConvert.toEntity(userForm);
        user.setAvatar("https://img0.baidu.com/it/u=2416850868,2392496129&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1693501200&t=722ff48c198154281bb273e2cdf919d9");
        user.setPassword("123456");
        this.save(user);
        for (Long orgId : userForm.getOrgIds()) {
            UserOrg userOrg = UserOrg.builder().userId(user.getId()).orgId(orgId).build();
            this.userOrgRepository.insert(userOrg);
        }
        return user.getId();
    }

    public UserForm findUserById(Long id) {
        User user = this.getById(id);
        LambdaQueryWrapper<UserOrg> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserOrg::getUserId,user.getId());
        List<UserOrg> userOrgs = this.userOrgRepository.selectList(queryWrapper);
        List<Long> orgIds = userOrgs.stream().map(UserOrg::getOrgId).toList();
        UserForm userForm = userConvert.toForm(user);
        userForm.setOrgIds(orgIds);
        return userForm;
    }
    @Transactional
    public Long modifyUserById(Long id, UserForm userForm) {
        User user = userConvert.toEntity(userForm);
        user.setId(id);
        this.updateById(user);
        LambdaQueryWrapper<UserOrg> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(UserOrg::getUserId,id);
        this.userOrgRepository.delete(queryWrapper);
        for (Long orgId : userForm.getOrgIds()) {
            UserOrg userOrg = UserOrg.builder().userId(user.getId()).orgId(orgId).build();
            this.userOrgRepository.insert(userOrg);
        }
        return id;
    }
}
