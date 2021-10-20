package com.hdr.demo.sharding.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class UserOrderMapperTest {

    @Autowired
    private UserOrderMapper userOrderMapper;

    @Test
    void init() {
        assert userOrderMapper != null;
    }

}