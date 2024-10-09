package com.tuling.dynamic.datasource.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.tuling.dynamic.datasource.dto.DataSourceDTO;
import com.tuling.dynamic.datasource.entity.Account;
import com.tuling.dynamic.datasource.entity.Ds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description:
 */
@DS("account")
public interface AccountMapper {
    @Select("SELECT * FROM account")
    List<Account> list();

    @Select("SELECT * FROM account WHERE id =#{id}")
    Account selectById(Long id);

    @Update("UPDATE account SET balance=#{account.balance}  WHERE id =#{account.id}")
    void updateById(@Param("account") Account account);
}