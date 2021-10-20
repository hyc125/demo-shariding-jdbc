package com.hdr.demo.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdr.demo.sharding.dao.UserOrderMapper;
import com.hdr.demo.sharding.entity.UserOrder;

/**
 * @author hyc
 */
@SpringBootApplication
public class ShardingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ShardingApplication.class, args);
        UserOrderMapper userOrderMapper = ctx.getBean(UserOrderMapper.class);
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.eq("user_id", 1);
        System.err.println(userOrderMapper.selectList(query));
    }
}
