package ru.project.shift.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.shift.model.Data;
import ru.project.shift.model.OptionHolder;
import ru.project.shift.filter.DataFilter;
import ru.project.shift.filter.Filter;
import ru.project.shift.parser.CmdParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(CmdParser.class.getName());
    private final OptionHolder optionHolder;
    private final List<Data> dataList = new ArrayList<>();

    public DataProcessor(OptionHolder optionHolder) {
        this.optionHolder = optionHolder;
    }

    @Override
    public void process() {
        Filter dataFilter = new DataFilter();
        Data data = new Data();
        for (String file : optionHolder.getInputFiles()) {
            Path filePath = Path.of(file);
            if (!Files.exists(filePath)) {
                LOG.error(String.format("Файл %s не существует", filePath));
                continue;
            }
            try (var reader = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    dataFilter.filter(data, line.trim());
                }
            } catch (IOException e) {
                LOG.error(String.format("Ошибка чтения файла: %s", file), e);
            }
            dataList.add(data);
            if (optionHolder.isAppendMode()) {
                data = new Data();
            }
        }
    }

    @Override
    public List<Data> getData() {
        return dataList;
    }
}
