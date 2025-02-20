package ru.project.shift.statistic;

public interface Printer {
    void printForInteger(StatisticHolder statisticHolder);

    void printForDouble(StatisticHolder statisticHolder);

    void printForString(StatisticHolder statisticHolder);
}
