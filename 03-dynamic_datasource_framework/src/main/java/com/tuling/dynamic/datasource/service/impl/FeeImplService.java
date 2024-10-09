package com.tuling.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.mapper.FeeMapper;
import com.tuling.dynamic.datasource.entity.Fee;
import com.tuling.dynamic.datasource.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 *
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class FeeImplService implements FeeService {

    @Resource
    FeeMapper feeMapper;

    public List<Fee> listAll() {
        return feeMapper.list();
    }

    @Override
    @DS("slave")  // 从库， 如果按照下划线命名方式配置多个  ， 可以指定前缀即可（组名）
    public List<Fee> list() {
        return feeMapper.list();
    }

    @Override
    public void save(Fee Fee) {
        feeMapper.save(Fee);
    }

    @Override
    public void delete(int id) {
        feeMapper.delete(id);
    }


    @DS("master")
    @DSTransactional
    public void saveAll() {
        // 执行多数据源的操作
    }

}
