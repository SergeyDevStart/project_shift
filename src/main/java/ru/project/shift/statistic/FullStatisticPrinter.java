package ru.project.shift.statistic;

public class FullStatisticPrinter implements Printer {
    @Override
    public void printForInteger(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Полная статистика для Целых чисел:
                Количество элементов: %d
                Минимальное значение: %d
                Максимальное значение: %d
                Среднее значение: %.2f
                Сумма: %d
                """,
                statisticHolder.getCount(),
                (long) statisticHolder.getMin(),
                (long) statisticHolder.getMax(),
                statisticHolder.getAvg(),
                (long) statisticHolder.getSum()
        );
    }

    @Override
    public void printForDouble(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Полная статистика для вещественных чисел:
                Количество элементов: %d
                Минимальное значение: %f
                Максимальное значение: %f
                Среднее значение: %f
                Сумма: %f
                """,
                statisticHolder.getCount(),
                statisticHolder.getMin(),
                statisticHolder.getMax(),
                statisticHolder.getAvg(),
                statisticHolder.getSum()
        );
    }

    @Override
    public void printForString(StatisticHolder statisticHolder) {
        System.out.printf(
                """
                Полная статистика для строк:
                Количество элементов: %d
                Длина самой короткой строки: %d
                Длина самой длинной строки: %d
                """,
                statisticHolder.getCount(),
                Math.round(statisticHolder.getMin()),
                Math.round(statisticHolder.getMax())
        );
    }
}
