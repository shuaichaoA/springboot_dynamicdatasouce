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

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.tuling.dynamic.datasource.dto.DataSourceDTO;
import com.tuling.dynamic.datasource.entity.Ds;
import com.tuling.dynamic.datasource.service.DsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/datasources")
public class DataSourceController {

    private final DataSource dataSource;
    private final DefaultDataSourceCreator dataSourceCreator;
    @Resource
    private DsService dsService;

    public DataSourceController(DataSource dataSource, DefaultDataSourceCreator dataSourceCreator) {
        this.dataSource = dataSource;
        this.dataSourceCreator = dataSourceCreator;
    }

    /**
     * 获取当前所有数据源
     */
    @GetMapping
    public List<Ds> now() {
//        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
//        Map<String, DataSource> dataSources = ds.getDataSources();
//        return dataSources.keySet();
        return dsService.list();
    }

    /**
     * 添加数据源
     */
    @PostMapping
    public Set<String> add(@Validated @RequestBody DataSourceDTO dto) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        BeanUtils.copyProperties(dto, dataSourceProperty);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dto.getPoolName(), dataSource);
        Ds dsEntity = new Ds();
        BeanUtils.copyProperties(dto, dsEntity);
        dsEntity.setPool_name(dto.getPoolName());
        dsEntity.setDriver_class_name(dto.getDriverClassName());
        dsService.save(dsEntity);
        return ds.getDataSources().keySet();
    }

    /**
     * 删除数据源
     */
    @DeleteMapping
    public String remove(@RequestParam String name) {
        dsService.delete(name);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(name);
        return "删除成功";
    }

    @PutMapping
    public Set<String> update(@RequestBody DataSourceDTO dto) {
        dsService.update(dto);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        ds.removeDataSource(dto.getPoolName());
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        dataSourceProperty.getDruid().setBreakAfterAcquireFailure(true);
        dataSourceProperty.getDruid().setConnectionErrorRetryAttempts(3);
        BeanUtils.copyProperties(dto, dataSourceProperty);
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        ds.addDataSource(dto.getPoolName(), dataSource);
        return ds.getDataSources().keySet();
    }
}
