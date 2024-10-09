package com.tuling.dynamic.datasource.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.tuling.dynamic.datasource.entity.Order;
import com.tuling.dynamic.datasource.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 */
@DS("product")
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<Product> list();

    @Select("SELECT * FROM product WHERE id=#{id}")
    Product selectById(Long id);

    @Update("UPDATE product SET stock=#{product.stock} WHERE id =#{product.id}")
    void updateById(@Param("product") Product product);

    @Insert("INSERT INTO `p_order` (`user_id`, `product_id`, `amount`, `total_price`, `status`) VALUES (#{order.userId},#{order.productId},#{order.amount},#{order.totalPrice},#{order.status})")
    void insert(@Param("product") Product product);
}