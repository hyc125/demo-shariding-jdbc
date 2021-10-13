package com.hdr.demo.sharding.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hdr.demo.sharding.annotation.DataSharding;
import com.hdr.demo.sharding.annotation.InlineShardingStrategy;
import com.hdr.demo.sharding.annotation.ShardingStrategyType;
import lombok.Data;

/**
 * @author hyc
 * @since 2021/9/23 17:07
 */
@DataSharding(
    logicTable = "user_order",
    actualDataNodes = "order_centre.user_order_${0..7}",
    tableShardingStrategyType = ShardingStrategyType.InlineShardingStrategy,
    tableInlineShardingStrategy = @InlineShardingStrategy(shardingColumn = "user_id", algorithmExpression = "user_order_$->{user_id % 8}")
)
@Data
@TableName("user_order")
public class UserOrder {

    @TableId("order_id")
    private Long orderId;

    @TableField("user_id")
    private Long userId;
}
