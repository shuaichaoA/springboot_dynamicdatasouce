package com.tuling.dynamic.datasource.mapper;


import com.tuling.dynamic.datasource.entity.Fee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: wangyi
 * @Date: 2020/12/12 01:16
 * @Description: 
 */
public interface FeeMapper   {
    @Select("SELECT * FROM Fee")
    List<Fee> list();

    @Insert("INSERT INTO  Fee(`name`,`fee`) VALUES (#{name},#{fee})")
    void save(Fee Fee);

    @Insert("DELETE FROM Fee WHERE id=#{id}")
    void delete(int id);
}