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
package com.mszlu.msauth.admin.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mszlu.msauth.admin.model.entity.Organization;
import com.mszlu.msauth.admin.model.serialize.ListLongToStringArrayJsonSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 码神之路
 */
@Data
public class UserForm {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String name;

    private Integer gender;

    private String email;

    private String mobile;

    private String avatar;

    private Integer status;

    @JsonSerialize(using = ListLongToStringArrayJsonSerializer.class)
    private List<Long> orgIds;
}
