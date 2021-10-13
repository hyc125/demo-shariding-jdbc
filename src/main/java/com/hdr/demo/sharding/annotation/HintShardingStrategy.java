package com.hdr.demo.sharding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
/**
 * @author hyc
 * @since 2021/10/11 14:58
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HintShardingStrategy {

    @SuppressWarnings("rawtypes")
    Class<? extends HintShardingAlgorithm> hintShardingAlgorithm();

}
