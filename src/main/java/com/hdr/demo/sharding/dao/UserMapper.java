package com.hdr.demo.sharding.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hdr.demo.sharding.entity.User;
/**
 * @author hyc
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {



}
