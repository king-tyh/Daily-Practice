package com.mybatis.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;

import javax.sql.DataSource;

public class DruidDataSourceFactory extends PooledDataSourceFactory {

    public DruidDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }

}
