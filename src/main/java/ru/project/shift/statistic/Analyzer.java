package ru.project.shift.statistic;

import ru.project.shift.model.Data;

import java.util.List;
import java.util.Map;

public interface Analyzer {
    Map<String, StatisticHolder> analyze(List<Data> dataList);
}
