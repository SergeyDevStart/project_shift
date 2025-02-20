package ru.project.shift.statistic;

import ru.project.shift.model.Data;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticAnalyzer implements Analyzer {
    @Override
    public Map<String, StatisticHolder> analyze(List<Data> dataList) {
        Map<String, StatisticHolder> result = new HashMap<>();
        Data data = merge(dataList);
        if (data.getIntegers() != null && !data.getIntegers().isEmpty()) {
            result.put("integers", analyzeInteger(data.getIntegers()));
        }
        if (data.getDoubles() != null && !data.getDoubles().isEmpty()) {
            result.put("doubles", analyzeDouble(data.getDoubles()));
        }
        if (data.getStrings() != null && !data.getStrings().isEmpty()) {
            result.put("strings", analyzeString(data.getStrings()));
        }
        return result;
    }

    private StatisticHolder analyzeInteger(List<Long> integers) {
        LongSummaryStatistics stat = integers.stream().collect(Collectors.summarizingLong(Long::longValue));
        return new StatisticHolder(stat.getCount(), stat.getMin(), stat.getMax(), stat.getAverage(), stat.getSum());
    }

    private StatisticHolder analyzeDouble(List<Double> doubles) {
        DoubleSummaryStatistics stat = doubles.stream().collect(Collectors.summarizingDouble(Double::doubleValue));
        return new StatisticHolder(stat.getCount(), stat.getMin(), stat.getMax(), stat.getAverage(), stat.getSum());
    }

    private StatisticHolder analyzeString(List<String> strings) {
        StatisticHolder statisticHolder = new StatisticHolder();
        statisticHolder.setCount(strings.size());
        statisticHolder.setMin(strings.stream()
                .map(String::length)
                .min(Integer::compareTo)
                .orElse(0));
        statisticHolder.setMax(strings.stream()
                .map(String::length)
                .max(Integer::compareTo)
                .orElse(0));
        return statisticHolder;
    }

    private Data merge(List<Data> dataList) {
        Data targetData = new Data();
        for (Data sourceData : dataList) {
            targetData.getIntegers().addAll(sourceData.getIntegers());
            targetData.getDoubles().addAll(sourceData.getDoubles());
            targetData.getStrings().addAll(sourceData.getStrings());
        }
        return targetData;
    }
}
