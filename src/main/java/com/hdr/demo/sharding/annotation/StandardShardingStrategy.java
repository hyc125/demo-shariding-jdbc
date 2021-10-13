package com.hdr.demo.sharding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
/**
 * @author hyc
 * @since 2021/10/7 15:54
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StandardShardingStrategy{

    String shardingColumn();

    @SuppressWarnings("rawtypes")
    Class<? extends PreciseShardingAlgorithm> preciseShardingAlgorithm();

    Class<? extends RangeShardingAlgorithm> rangeShardingAlgorithm() default RangeShardingAlgorithm.class;
}
