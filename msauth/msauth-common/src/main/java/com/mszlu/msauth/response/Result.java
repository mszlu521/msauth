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
package com.mszlu.msauth.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 码神之路
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
   private int code;
   private String msg;
   private T data;

   public static <T> Result<T> success(T data){
       return new Result<>(200,"success",data);
   }
    public static <T> Result<T> fail(int code, String msg){
        return new Result<>(code,msg,null);
    }
}
