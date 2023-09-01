package com.mszlu.msauth.admin.controller;

import com.mszlu.msauth.admin.model.dto.OrganizationDTO;
import com.mszlu.msauth.admin.model.dto.OrganizationForm;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.vo.OrganizationVO;
import com.mszlu.msauth.admin.service.OrganizationService;
import com.mszlu.msauth.model.Option;
import com.mszlu.msauth.response.PageResult;
import com.mszlu.msauth.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 码神之路
 */
@RestController
@RequestMapping("/api/v1/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/list")
    public Result<List<OrganizationVO>> listOrganization(@RequestBody(required = false) OrganizationDTO org){
        return Result.success(organizationService.listOrganization(org));
    }
    @PostMapping("/options")
    public Result<List<Option<String>>> options(){
        return Result.success(organizationService.listOptions());
    }
    @PostMapping("/add")
    public Result<Long> add(@RequestBody OrganizationForm organizationForm){
        return Result.success(organizationService.add(organizationForm));
    }
    @PostMapping("{id}")
    public Result<OrganizationForm> detail(@PathVariable Long id){
        return Result.success(organizationService.findOrgDetailById(id));
    }
    @PostMapping("{id}/update")
    public Result<Long> update(@PathVariable Long id, @RequestBody OrganizationForm form){
        return Result.success(organizationService.modifyOrg(id,form));
    }
}
