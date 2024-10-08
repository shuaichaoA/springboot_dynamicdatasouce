package com.tuling.dynamic.datasource.service;

import com.tuling.dynamic.datasource.dto.DataSourceDTO;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.entity.Fee;

import java.util.List;

/***
 *
 * @Slogan 致敬大师，致敬未来的你
 */
public interface DsService {
    List<Ds> list();

    void save(Ds ds);

    void delete(String name);
    void update(DataSourceDTO dto);

}
