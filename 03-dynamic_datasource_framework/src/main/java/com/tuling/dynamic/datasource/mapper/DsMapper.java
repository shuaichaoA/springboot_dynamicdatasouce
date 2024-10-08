package com.tuling.dynamic.datasource.mapper;


import com.tuling.dynamic.datasource.dto.DataSourceDTO;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.entity.Fee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description: 
 */
public interface DsMapper {
    @Select("SELECT * FROM data_source")
    List<Ds> list();

    @Insert("INSERT INTO `data_source`(`pool_name`, `username`, `password`, `url`, `driver_class_name`, `type`) VALUES (#{pool_name}, #{username}, #{password}, #{url}, #{driver_class_name}, #{type} )")
    void save(Ds ds);

    @Insert("DELETE FROM data_source WHERE pool_name=#{name}")
    void delete(String name);

    @Update("UPDATE data_source SET `username`=#{ds.username},`password`=#{ds.password},`url`=#{ds.url},`driver_class_name`=#{ds.driverClassName},`type`=#{ds.type}  WHERE pool_name=#{ds.poolName}")
    void update(@Param("ds")DataSourceDTO dto);
}