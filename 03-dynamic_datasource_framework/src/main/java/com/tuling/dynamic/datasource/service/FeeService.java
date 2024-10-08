package com.tuling.dynamic.datasource.service;

import com.tuling.dynamic.datasource.entity.Fee;

import java.util.List;

/***
 *
 * @Slogan 致敬大师，致敬未来的你
 */
public interface FeeService {
    List<Fee> listAll();

    List<Fee> list();

    void save(Fee Fee);

    void delete(int id);

}
