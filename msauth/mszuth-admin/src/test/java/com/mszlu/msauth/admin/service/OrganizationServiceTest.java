package com.mszlu.msauth.admin.service;

import com.mszlu.msauth.admin.model.dto.OrganizationDTO;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.response.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 码神之路
 */
@SpringBootTest
public class OrganizationServiceTest {
    @Autowired
    private OrganizationService organizationService;

    @Test
    public void testFindAll(){
    }
}
