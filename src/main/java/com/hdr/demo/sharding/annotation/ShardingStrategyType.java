package com.hdr.demo.sharding.annotation;

/**
 * @author hyc
 * @since 2021/10/11 11:10
 */
public enum ShardingStrategyType {
    NoShardingStrategy(),
    InlineShardingStrategy(),
    StandardShardingStrategy(),
    ComplexShardingStrategy(),
    HintShardingStrategy(),
    ;

    ShardingStrategyType() {
    }
}
