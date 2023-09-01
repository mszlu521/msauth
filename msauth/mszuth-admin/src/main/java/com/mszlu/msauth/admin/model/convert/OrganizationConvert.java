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
package com.mszlu.msauth.admin.model.convert;

import com.mszlu.msauth.admin.model.dto.OrganizationForm;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.vo.OrganizationVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 码神之路
 */
@Mapper(componentModel = "Spring")
public interface OrganizationConvert {

    OrganizationVO toVO(Organization org);

    Organization toEntity(OrganizationForm form);

    OrganizationForm toForm(Organization org);
}
