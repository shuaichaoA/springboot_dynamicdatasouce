package com.tuling.dynamic.datasource.filter;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Map;

@Slf4j
@WebFilter(filterName = "dsFilter", urlPatterns = {"/fee/*"})
public class DynamicDatasourceFilter implements Filter {

    private final DataSource dataSource;

    public DynamicDatasourceFilter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("loading filter {}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestURI = request.getRequestURI();

        String headerProvince = StringUtils.hasLength(request.getHeader("province")) ? request.getHeader("province") : "";

        String dsKey = "master";
        if (headerProvince.equals("shandong")) {
            dsKey = "slave";
        }
        log.info("操作类型{} 当前路径是{} 省分标识{} 数据库key[{}] ", request.getMethod(), requestURI, headerProvince, dsKey);
        DynamicRoutingDataSource ds = (DynamicRoutingDataSource) dataSource;
        Map<String, DataSource> dataSources = ds.getDataSources();
        // 执行
        try {
            DynamicDataSourceContextHolder.push(dsKey);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            DynamicDataSourceContextHolder.poll();
        }
    }

    @Override
    public void destroy() {

    }
}
