/*
 * Copyright © ${project.inceptionYear} organization baomidou
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
package com.tuling.dynamic.datasource.entity;


import com.tuling.dynamic.datasource.common.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Order {

    private Integer id;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 订单状态
     */
    private OrderStatus status;
    /**
     * 数量
     */
    private Integer amount;
    /**
     * 总金额
     */
    private Double totalPrice;
}