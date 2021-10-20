package com.hdr.demo.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
 * @author hyc
 */
@Data
@TableName("user")
public class User {

    @TableField("user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("age")
    private Integer age;

    @TableField("phone_number")
    private String phoneNumber;

}
