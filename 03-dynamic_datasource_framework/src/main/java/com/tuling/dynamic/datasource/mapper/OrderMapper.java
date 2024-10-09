package com.tuling.dynamic.datasource.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.tuling.dynamic.datasource.entity.Account;
import com.tuling.dynamic.datasource.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Description:
 */
@DS("order")
public interface OrderMapper {

    @Select("SELECT * FROM p_order")
    List<Order> list();

    @Update("UPDATE p_order SET status=#{order.status},total_price=#{order.totalPrice} WHERE id =#{order.id}")
    void updateById(@Param("order") Order order);


    @Insert("INSERT INTO `p_order` (`user_id`, `product_id`, `amount`, `total_price`, `status`) VALUES (#{order.userId},#{order.productId},#{order.amount},#{order.totalPrice},#{order.status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("order") Order order);
}