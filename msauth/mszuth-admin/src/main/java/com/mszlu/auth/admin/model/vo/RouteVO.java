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
package com.mszlu.auth.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author 码神之路
 */
@Data
@Schema(description = "路由对象")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouteVO {
    @Schema(description = "路由路径", example = "user")
    private String path;

    @Schema(description = "组件路径", example = "system/user/index")
    private String component;

    @Schema(description = "跳转链接", example = "")
    private String redirect;

    @Schema(description = "路由名称")
    private String name;
    @Schema(description = "路由属性")
    private Meta meta;
    @Schema(description = "路由属性类型")
    @Data
    public static class Meta {

        @Schema(description = "路由title")
        private String title;

        @Schema(description = "ICON")
        private String icon;

        @Schema(description = "是否隐藏", example = "true")
        private Boolean hidden;

        @Schema(description = "拥有路由权限的角色编码", example = "['ADMIN','ROOT']")
        private List<String> roles;

        @Schema(description = "是否开启缓存", example = "true")
        private Boolean keepAlive;
    }

    @Schema(description = "子路由列表")
    private List<RouteVO> children;
}
