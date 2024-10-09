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
package com.tuling.dynamic.datasource.controller;


import com.tuling.dynamic.datasource.dto.PlaceOrderRequest;
import com.tuling.dynamic.datasource.entity.Fee;
import com.tuling.dynamic.datasource.entity.Order;
import com.tuling.dynamic.datasource.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping
    public List<Order> select() {
        return orderService.list();
    }

//    @Operation(summary = "自由下单", description = "比如 1号用户购买1号商品1个")
    @PostMapping("/placeOrder")
    public String placeOrder(@Validated @RequestBody PlaceOrderRequest request) {
        orderService.placeOrder(request);
        return "下单成功";
    }

//    @Operation(summary = "测试商品库存不足回滚", description = "商品单价10元，库存20个,用户余额50元，模拟一次性购买22个。 期望异常回滚")
    @PostMapping("/overstock")
    public String overstock() {
        orderService.placeOrder(new PlaceOrderRequest(1L, 1L, 22));
        return "下单成功";
    }

//    @Operation(summary = "测试用户账户余额不足回滚", description = "商品单价10元，库存20个，用户余额50元，模拟一次性购买6个。 期望异常回滚")
    @PostMapping("/overbalance")
    public String overbalance() {
        orderService.placeOrder(new PlaceOrderRequest(1L, 1L, 6));
        return "下单成功";
    }
}