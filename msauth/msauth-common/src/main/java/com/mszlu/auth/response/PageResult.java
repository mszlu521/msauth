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
package com.mszlu.auth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 码神之路
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

    private int total;
    private int page;
    private int pageSize;
    private int pageCount;
    private List<T> list;

    public static <T> PageResult<T> of(List<T> list,int total, int page, int pageSize){
        PageResult<T> pageResult = new PageResult<>(total,page,pageSize,0,list);
        pageResult.pageCount = getPageCount(total,pageSize);
        return pageResult;
    }

    public static int getPageCount(int total, int pageSize){
        return ((total-1)/pageSize)+1;
    }

    public static <T> PageResult<T> of(Page<T> all,int page, int pageSize) {
        return new PageResult<>(all.getSize(),page,pageSize,all.getTotalPages(),all.getContent());
    }
}
