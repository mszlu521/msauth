package com.mszlu.auth.admin.repository;

import com.mszlu.auth.admin.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 码神之路
 */
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
}
