package ru.project.shift.statistic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.shift.model.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StatisticAnalyzerTest {
    private StatisticAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new StatisticAnalyzer();
    }

    @Test
    void whenAnalyzeIntegersThenSuccessful() {
        Data dataOne = new Data();
        Data dataTwo = new Data();
        dataOne.addInteger(10L);
        dataOne.addInteger(20L);
        dataTwo.addInteger(30L);

        List<Data> dataList = Arrays.asList(dataOne, dataTwo);
        Map<String, StatisticHolder> result = analyzer.analyze(dataList);

        assertNotNull(result.get("integers"));
        StatisticHolder statisticResult = result.get("integers");
        assertThat(statisticResult.getCount()).isEqualTo(3L);
        assertThat(statisticResult.getMin()).isEqualTo(10L);
        assertThat(statisticResult.getMax()).isEqualTo(30L);
        assertThat(statisticResult.getAvg()).isEqualTo(20.0);
        assertThat(statisticResult.getSum()).isEqualTo(60L);
    }

    @Test
    void whenAnalyzeStringsThenSuccessful() {
        Data data = new Data();
        data.addString("This");
        data.addString("is");
        data.addString("test");
        data.addString("Hello World");

        List<Data> dataList = List.of(data);
        Map<String, StatisticHolder> result = analyzer.analyze(dataList);

        assertNotNull(result.get("strings"));
        StatisticHolder statisticResult = result.get("strings");
        assertThat(statisticResult.getCount()).isEqualTo(4L);
        assertThat(statisticResult.getMin()).isEqualTo(2L);
        assertThat(statisticResult.getMax()).isEqualTo(11L);
    }

    @Test
    void whenAnalyzeDoublesThenSuccessful() {
        Data data = new Data();
        data.addDouble(15.5);
        data.addDouble(25.5);

        List<Data> dataList = List.of(data);
        Map<String, StatisticHolder> result = analyzer.analyze(dataList);

        assertNotNull(result.get("doubles"));
        StatisticHolder statisticResult = result.get("doubles");
        assertThat(statisticResult.getCount()).isEqualTo(2L);
        assertThat(statisticResult.getMin()).isEqualTo(15.5);
        assertThat(statisticResult.getMax()).isEqualTo(25.5);
        assertThat(statisticResult.getAvg()).isEqualTo(20.5);
        assertThat(statisticResult.getSum()).isEqualTo(41.0);
    }

    @Test
    void whenNoDataThenNothingHappens() {
        List<Data> dataList = List.of(new Data());
        Map<String, StatisticHolder> result = analyzer.analyze(dataList);

        assertThat(result).isEmpty();
    }
}