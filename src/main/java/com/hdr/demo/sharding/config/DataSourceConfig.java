package com.hdr.demo.sharding.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;
/**
 * @author hyc
 * @since 2021/9/23 16:49
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        return getShardingSphereDataSource();
    }

    private DataSource getShardingSphereDataSource() {
        Properties properties = new Properties();
        properties.setProperty("sql.show", "true");
        try {
            return ShardingDataSourceFactory.createDataSource(getDataSourceMap(), getRuleConfigurations(), properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private ShardingRuleConfiguration getRuleConfigurations() {
        ShardingRuleConfiguration ruleConfiguration = new ShardingRuleConfiguration();
        ruleConfiguration.setTableRuleConfigs(DataShardingProcessor.buildTableRuleConfigurations());
        return ruleConfiguration;
    }

    private Map<String, DataSource> getDataSourceMap() {
        Map<String, DataSource> map = new HashMap<>(2);
        map.put("order_centre", getHikariDataSource());
        return map;
    }

    private HikariDataSource getHikariDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/order_centre");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
}
