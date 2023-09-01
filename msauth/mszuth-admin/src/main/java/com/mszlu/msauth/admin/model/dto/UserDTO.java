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

import lombok.Data;

/**
 * @author 码神之路
 */
@Data
public class UserDTO {

    private String keywords;

    private Integer status;

    private Long orgId;

    private Integer pageNum;

    private Integer pageSize;

    public void fillPage() {
        if (this.pageNum == null){
            this.pageNum = 1;
        }
        if (this.pageSize == null){
            this.pageSize = 10;
        }
    }
}