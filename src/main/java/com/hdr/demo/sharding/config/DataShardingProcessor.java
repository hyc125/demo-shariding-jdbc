package com.hdr.demo.sharding.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.NoneShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.reflections.Reflections;
import com.hdr.demo.sharding.annotation.DataSharding;
import com.hdr.demo.sharding.annotation.ShardingStrategyType;
import static com.hdr.demo.sharding.annotation.ShardingStrategyType.ComplexShardingStrategy;
import static com.hdr.demo.sharding.annotation.ShardingStrategyType.HintShardingStrategy;
import static com.hdr.demo.sharding.annotation.ShardingStrategyType.InlineShardingStrategy;
import static com.hdr.demo.sharding.annotation.ShardingStrategyType.NoShardingStrategy;
import static com.hdr.demo.sharding.annotation.ShardingStrategyType.StandardShardingStrategy;
import com.hdr.demo.sharding.annotation.StandardShardingStrategy;
/**
 * @author hyc
 * @since 2021/10/11 11:35
 */
public class DataShardingProcessor {

    public static Set<Class<?>> getClassWithAnnotated(Class<? extends Annotation> annotationClass) {
        Reflections reflections = new Reflections("com.hdr.demo.sharding");
        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    public static Set<Class<?>> getClassWithAnnotated(Class<? extends Annotation> annotationClass, String pkg) {
        Reflections reflections = new Reflections(pkg);
        return reflections.getTypesAnnotatedWith(annotationClass);
    }

    public static List<TableRuleConfiguration> buildTableRuleConfigurations() {
        Set<Class<?>> dataShardingEntitySet = getClassWithAnnotated(DataSharding.class);
        return dataShardingEntitySet.stream()
                                    .map(clazz -> clazz.getAnnotation(DataSharding.class))
                                    .map(DataShardingProcessor::doBuildTableRuleConfiguration)
                                    .collect(Collectors.toList());
    }

    private static TableRuleConfiguration doBuildTableRuleConfiguration(DataSharding dataSharding) {

        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(dataSharding.logicTable(), dataSharding.actualDataNodes());

        ShardingStrategyType dbShardingStrategy = dataSharding.dbShardingStrategyType();
        if (Objects.equals(dbShardingStrategy, ShardingStrategyType.NoShardingStrategy)) {

        } else if (Objects.equals(dbShardingStrategy, ShardingStrategyType.InlineShardingStrategy)) {
            tableRuleConfiguration.setDatabaseShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(dataSharding.dbInlineShardingStrategy().shardingColumn(), dataSharding.dbInlineShardingStrategy().algorithmExpression()));
        }


        ShardingStrategyType tableShardingStrategy = dataSharding.tableShardingStrategyType();
        if (Objects.equals(tableShardingStrategy, ShardingStrategyType.NoShardingStrategy)) {

        } else if (Objects.equals(tableShardingStrategy, ShardingStrategyType.InlineShardingStrategy)) {
            tableRuleConfiguration.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration(dataSharding.tableInlineShardingStrategy().shardingColumn(), dataSharding.tableInlineShardingStrategy().algorithmExpression()));
        }

        return tableRuleConfiguration;
    }

    private static Map<ShardingStrategyType, Function<DataSharding, ShardingStrategyConfiguration>> shardingStrategyConfigurationBuilder;

    static {
        shardingStrategyConfigurationBuilder = new HashMap<>();
        shardingStrategyConfigurationBuilder.put(NoShardingStrategy, (dataSharding -> new NoneShardingStrategyConfiguration()));
        shardingStrategyConfigurationBuilder.put(InlineShardingStrategy, (dataSharding -> new InlineShardingStrategyConfiguration(dataSharding.tableInlineShardingStrategy().shardingColumn(), dataSharding.tableInlineShardingStrategy().algorithmExpression())));
        shardingStrategyConfigurationBuilder.put(StandardShardingStrategy, (dataSharding -> {
            com.hdr.demo.sharding.annotation.StandardShardingStrategy standardShardingStrategy = dataSharding.tableStandardShardingStrategy();
            Class<? extends PreciseShardingAlgorithm> preciseShardingAlgorithm = standardShardingStrategy.preciseShardingAlgorithm();
            Class<? extends RangeShardingAlgorithm> rangeShardingAlgorithm = standardShardingStrategy.rangeShardingAlgorithm();
            if (Objects.equals(PreciseShardingAlgorithm.class, preciseShardingAlgorithm)) {
                throw new RuntimeException("illegal param for standardShardingStrategy.preciseShardingAlgorithm");
            }
            if (Objects.equals(RangeShardingAlgorithm.class, rangeShardingAlgorithm)) {
                try {
                    return new StandardShardingStrategyConfiguration(standardShardingStrategy.shardingColumn(), preciseShardingAlgorithm.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    return new StandardShardingStrategyConfiguration(standardShardingStrategy.shardingColumn(), preciseShardingAlgorithm.getConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }));
    }

}
