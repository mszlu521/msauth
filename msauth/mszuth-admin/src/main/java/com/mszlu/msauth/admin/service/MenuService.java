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
package com.mszlu.msauth.admin.service;

import com.mszlu.msauth.admin.model.vo.RouteVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 码神之路
 */
@Service
public class MenuService {

    public List<RouteVO> listRoutes() {
        //路由 从菜单得来
        List<RouteVO> routeVOList = new ArrayList<>();
        RouteVO routeVO = new RouteVO();
        routeVO.setPath("/organization");
        routeVO.setComponent("Layout");
        routeVO.setRedirect("/organization");
        RouteVO child = new RouteVO();
        routeVO.setChildren(List.of(child));
        routeVOList.add(routeVO);
        return routeVOList;
    }
}
