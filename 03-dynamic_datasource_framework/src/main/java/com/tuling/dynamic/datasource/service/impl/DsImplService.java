package com.tuling.dynamic.datasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.tuling.dynamic.datasource.dto.DataSourceDTO;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.entity.Fee;
import com.tuling.dynamic.datasource.mapper.DsMapper;
import com.tuling.dynamic.datasource.service.DsService;
import com.tuling.dynamic.datasource.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class DsImplService implements DsService {

    @Autowired
    DsMapper dsMapper;


    @Override
    @DS("master")  // 从库， 如果按照下划线命名方式配置多个  ， 可以指定前缀即可（组名）
    public List<Ds> list() {
        return dsMapper.list();
    }

    @Override
    public void save(Ds ds) {
        dsMapper.save(ds);
    }

    @Override
    public void delete(String name) {
        dsMapper.delete(name);
    }

    @Override
    public void update(DataSourceDTO dto) {
        dsMapper.update(dto);
    }


}
