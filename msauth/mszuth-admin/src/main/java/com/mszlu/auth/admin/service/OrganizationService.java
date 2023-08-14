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

import com.mszlu.auth.admin.model.convert.OrganizationConvert;
import com.mszlu.auth.admin.model.dto.OrganizationDTO;
import com.mszlu.auth.admin.model.entity.Organization;
import com.mszlu.auth.admin.model.vo.OrganizationVO;
import com.mszlu.auth.admin.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 码神之路
 */
@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationConvert organizationConvert;
    private final Long ROOT = 0L;
    @Autowired
    private OrganizationRepository organizationRepository;

    public List<OrganizationVO> list(OrganizationDTO organizationDTO){
        List<Organization> organizationList = organizationRepository.findAll(Sort.by(Sort.Order.asc("sort")));
        //组织为一个树形结构
        return toTreeList(ROOT,organizationList);
    }

    private List<OrganizationVO> toTreeList(Long parentId, List<Organization> organizationList) {
        return organizationList.stream().
                filter(organization -> organization.getParentId().equals(parentId)).
                map(organization -> {
                    OrganizationVO organizationVO = organizationConvert.toVO(organization);
                    organizationVO.setChildren(toTreeList(organization.getId(), organizationList));
                    return organizationVO;
                }).
                collect(Collectors.toList());
    }
}
