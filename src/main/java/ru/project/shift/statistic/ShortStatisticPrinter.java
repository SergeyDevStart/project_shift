package ru.project.shift.statistic;

public class ShortStatisticPrinter implements Printer {
    @Override
    public void printForInteger(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Краткая статистика для целых чисел:
                Количество элементов: %d%n
                """, statisticHolder.getCount()
        );
    }

    @Override
    public void printForDouble(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Краткая статистика для вещественных чисел:
                Количество элементов: %d%n
                """, statisticHolder.getCount()
        );
    }

    @Override
    public void printForString(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Краткая статистика для строк:
                Количество элементов: %d%n
                """, statisticHolder.getCount()
        );
    }
}
