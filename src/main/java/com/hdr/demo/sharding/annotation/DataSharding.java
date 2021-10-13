package com.hdr.demo.sharding.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
/**
 * @author hyc
 * @since 2021/10/7 13:13
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSharding {

    String logicTable();

    String actualDataNodes();

    // ----- Java注解不支持多态真的太痛苦了0.0 -----

    ShardingStrategyType dbShardingStrategyType() default ShardingStrategyType.NoShardingStrategy;

    ShardingStrategyType tableShardingStrategyType() default ShardingStrategyType.NoShardingStrategy;

    NoShardingStrategy dbNoShardingStrategy() default @NoShardingStrategy;

    NoShardingStrategy tableNoShardingStrategy() default @NoShardingStrategy;

    InlineShardingStrategy dbInlineShardingStrategy() default @InlineShardingStrategy(shardingColumn = "", algorithmExpression = "");

    InlineShardingStrategy tableInlineShardingStrategy() default @InlineShardingStrategy(shardingColumn = "", algorithmExpression = "");

    StandardShardingStrategy dbStandardShardingStrategy() default @StandardShardingStrategy(shardingColumn = "", preciseShardingAlgorithm = PreciseShardingAlgorithm.class);

    StandardShardingStrategy tableStandardShardingStrategy() default @StandardShardingStrategy(shardingColumn = "", preciseShardingAlgorithm = PreciseShardingAlgorithm.class);

    ComplexShardingStrategy dbComplexShardingStrategy() default @ComplexShardingStrategy(shardingColumns = "", shardingAlgorithm = ComplexKeysShardingAlgorithm.class);

    ComplexShardingStrategy tableComplexShardingStrategy() default @ComplexShardingStrategy(shardingColumns = "", shardingAlgorithm = ComplexKeysShardingAlgorithm.class);

    HintShardingStrategy dbHintShardingStrategy() default @HintShardingStrategy(hintShardingAlgorithm = HintShardingAlgorithm.class);

    HintShardingStrategy tableHintShardingStrategy() default @HintShardingStrategy(hintShardingAlgorithm = HintShardingAlgorithm.class);

}
