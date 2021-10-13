package com.hdr.demo.sharding.config;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.reflections.Reflections;
import com.hdr.demo.sharding.annotation.DataSharding;
import com.hdr.demo.sharding.annotation.ShardingStrategyType;
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

}
