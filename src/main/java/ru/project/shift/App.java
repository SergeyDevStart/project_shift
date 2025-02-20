package ru.project.shift;

import ru.project.shift.model.OptionHolder;
import ru.project.shift.parser.CmdParser;
import ru.project.shift.processor.DataProcessor;
import ru.project.shift.statistic.Analyzer;
import ru.project.shift.statistic.StatisticAnalyzer;
import ru.project.shift.statistic.StatisticPrinter;
import ru.project.shift.writer.DataWriter;

public class App {
    public void run(String[] args) {
        CmdParser parser = new CmdParser();
        try {
            OptionHolder optionHolder = parser.parsingArguments(args);
            DataProcessor dataProcessor = new DataProcessor(optionHolder);
            dataProcessor.process();
            DataWriter dataWriter = new DataWriter(dataProcessor.getData(), optionHolder);
            dataWriter.writeToFile();
            if (optionHolder.isShortStats() || optionHolder.isFullStats()) {
                Analyzer analyzer = new StatisticAnalyzer();
                StatisticPrinter statisticPrinter = new StatisticPrinter(optionHolder, analyzer, dataProcessor);
                statisticPrinter.print();
            }
        } catch (Exception e) {
            parser.printHelp();
            e.printStackTrace();
        }
    }
}
