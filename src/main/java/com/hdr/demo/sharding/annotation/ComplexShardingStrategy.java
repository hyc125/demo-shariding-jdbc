package com.hdr.demo.sharding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
/**
 * @author hyc
 * @since 2021/10/11 15:03
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ComplexShardingStrategy {

    String shardingColumns();

    @SuppressWarnings("rawtypes")
    Class<? extends ComplexKeysShardingAlgorithm> shardingAlgorithm();

}
