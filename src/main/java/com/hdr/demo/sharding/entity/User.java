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

    @TableId("id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("name")
    private String name;

    @TableField("age")
    private Integer age;

    @TableField("phone_number")
    private String phoneNumber;

}
