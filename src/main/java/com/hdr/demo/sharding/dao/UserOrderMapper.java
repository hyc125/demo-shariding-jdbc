package com.hdr.demo.sharding.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hdr.demo.sharding.entity.UserOrder;
/**
 * @author hyc
 * @since 2021/9/23 17:08
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {

}
