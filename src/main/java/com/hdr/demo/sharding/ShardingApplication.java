package com.hdr.demo.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdr.demo.sharding.dao.UserMapper;
import com.hdr.demo.sharding.dao.UserOrderMapper;
import com.hdr.demo.sharding.entity.User;
import com.hdr.demo.sharding.entity.UserOrder;

/**
 * @author hyc
 */
@SpringBootApplication
public class ShardingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ShardingApplication.class, args);
        System.err.println("-------------------------------");
        UserOrderMapper userOrderMapper = ctx.getBean(UserOrderMapper.class);
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.eq("user_id", 1);
        System.err.println(userOrderMapper.selectList(query));
        System.err.println("-------------------------------");
        UserMapper userMapper = ctx.getBean(UserMapper.class);
        QueryWrapper<User> q = new QueryWrapper<>();
        query.eq("user_id", 1);
        System.err.println(userMapper.selectList(q));
    }
}
