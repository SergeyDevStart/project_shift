package ru.project.shift.statistic;

import ru.project.shift.model.OptionHolder;
import ru.project.shift.processor.Processor;

import java.util.Map;

public class StatisticPrinter {
    private final OptionHolder optionHolder;
    private final Analyzer analyzer;
    private final Processor processor;

    public StatisticPrinter(OptionHolder optionHolder, Analyzer analyzer, Processor processor) {
        this.optionHolder = optionHolder;
        this.analyzer = analyzer;
        this.processor = processor;
    }

    public void print() {
        Map<String, StatisticHolder> statistic = analyzer.analyze(processor.getData());
        Printer printer;
        if (optionHolder.isShortStats()) {
            printer = new ShortStatisticPrinter();
        } else {
            printer = new FullStatisticPrinter();
        }
        if (statistic.containsKey("integers")) {
            printer.printForInteger(statistic.get("integers"));
        }
        if (statistic.containsKey("doubles")) {
            printer.printForDouble(statistic.get("doubles"));
        }
        if (statistic.containsKey("strings")) {
            printer.printForString(statistic.get("strings"));
        }
    }
}
