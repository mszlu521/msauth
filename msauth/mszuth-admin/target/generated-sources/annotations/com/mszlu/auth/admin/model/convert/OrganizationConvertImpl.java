package com.mszlu.auth.admin.model.convert;

import com.mszlu.auth.admin.model.entity.Organization;
import com.mszlu.auth.admin.model.vo.OrganizationVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-13T17:46:55+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OrganizationConvertImpl implements OrganizationConvert {

    @Override
    public OrganizationVO toVO(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationVO organizationVO = new OrganizationVO();

        organizationVO.setId( organization.getId() );
        organizationVO.setName( organization.getName() );
        organizationVO.setCode( organization.getCode() );
        organizationVO.setParentId( organization.getParentId() );
        organizationVO.setSort( organization.getSort() );
        organizationVO.setStatus( organization.getStatus() );
        organizationVO.setCreateTime( organization.getCreateTime() );
        organizationVO.setUpdateTime( organization.getUpdateTime() );

        return organizationVO;
    }
}
