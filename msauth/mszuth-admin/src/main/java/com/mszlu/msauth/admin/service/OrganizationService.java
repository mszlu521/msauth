package com.mszlu.msauth.admin.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mszlu.msauth.admin.model.convert.OrganizationConvert;
import com.mszlu.msauth.admin.model.dto.OrganizationDTO;
import com.mszlu.msauth.admin.model.dto.OrganizationForm;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.vo.OrganizationVO;
import com.mszlu.msauth.admin.repository.OrganizationRepository;
import com.mszlu.msauth.model.Option;
import com.mszlu.msauth.response.PageResult;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.SetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 码神之路
 */
@Service
@RequiredArgsConstructor
public class OrganizationService extends ServiceImpl<OrganizationRepository,Organization> {
    private  final OrganizationConvert organizationConvert;



    public List<OrganizationVO> listOrganization(OrganizationDTO org) {
        LambdaQueryWrapper<Organization> queryWrapper = Wrappers.lambdaQuery();
        if (org != null) {
            if (CharSequenceUtil.isNotBlank(org.getKeywords())) {
                queryWrapper.like(Organization::getName, org.getKeywords());
            }
            if (org.getStatus() != null) {
                queryWrapper.eq(Organization::getStatus, org.getStatus());
            }
        }
        queryWrapper.orderByAsc(Organization::getSort);
        List<Organization> organizationList = this.list(queryWrapper);
        //搜索出来的结果 节点不一定是从root 顶级节点开始
        //找出目前真正的 根节点
        Set<Long> parentIds = organizationList.stream().map(Organization::getParentId).collect(Collectors.toSet());
        Set<Long> ids = organizationList.stream().map(Organization::getId).collect(Collectors.toSet());
        List<Long> rootIds = CollUtil.subtractToList(parentIds, ids);
        List<OrganizationVO> organizationVOList = new ArrayList<>();
        for (Long rootId : rootIds) {
            organizationVOList.addAll(toTreeList(rootId, organizationList));
        }
        return organizationVOList;
    }

    private List<OrganizationVO> toTreeList(Long parentId, List<Organization> all) {
       return all.stream().
                filter(organization -> organization.getParentId().equals(parentId)).
                map(organization -> {
                    OrganizationVO organizationVO = organizationConvert.toVO(organization);
                    organizationVO.setChildren(toTreeList(organization.getId(),all));
                    return organizationVO;
                }).toList();
    }

    public List<Option<String>> listOptions() {
        LambdaQueryWrapper<Organization> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(Organization::getSort);
        List<Organization> organizationList = this.list(queryWrapper);
        return toTreeOptionList(0L,organizationList);
    }

    private List<Option<String>> toTreeOptionList(Long parentId, List<Organization> all) {
        return all.stream().
                filter(organization -> organization.getParentId().equals(parentId)).
                map(organization -> {
                    Option<String> option = new Option<>(organization.getId().toString(),organization.getName(),null);
                    option.setChildren(toTreeOptionList(organization.getId(),all));
                    return option;
                }).toList();
    }

    public Long add(OrganizationForm organizationForm) {
        Organization organization = organizationConvert.toEntity(organizationForm);
        this.save(organization);
        return organization.getId();
    }

    public OrganizationForm findOrgDetailById(Long id) {
        Organization organization = this.getById(id);
        return organizationConvert.toForm(organization);
    }

    public Long modifyOrg(Long id, OrganizationForm organizationForm) {
        organizationForm.setId(id);
        Organization organization = organizationConvert.toEntity(organizationForm);
        this.updateById(organization);
        return id;
    }
}
