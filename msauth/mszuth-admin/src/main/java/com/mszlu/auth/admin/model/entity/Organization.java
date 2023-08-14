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
package com.mszlu.auth.admin.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Objects;

/**
 * @author 码神之路
 */
@Data
@Entity
@Table(name = "ms_organization")
public class Organization {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment("组织名称")
    private String name;

    @Column(name = "code", nullable = false)
    @Comment("组织代码")
    private String code;
    @Column(name = "parent_id", nullable = false)
    @Comment("上级ID")
    private Long parentId;
    @Column(name = "sort", nullable = false)
    @Comment("排序")
    private Long sort;
    @Column(name = "status", nullable = false)
    @Comment("状态 1 正常 0 禁用")
    private Integer status;

    @CreatedDate
    private Date createTime;
    @LastModifiedDate
    private Date updateTime;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Organization that = (Organization) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
