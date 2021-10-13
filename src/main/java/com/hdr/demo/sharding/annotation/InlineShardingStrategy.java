package com.hdr.demo.sharding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author hyc
 * @since 2021/10/7 13:18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InlineShardingStrategy {

    String shardingColumn();

    String algorithmExpression();
}
