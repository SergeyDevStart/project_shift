package ru.project.shift.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.shift.filter.DataFilter;
import ru.project.shift.filter.Filter;
import ru.project.shift.model.Data;
import ru.project.shift.model.OptionHolder;
import ru.project.shift.reader.DataReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor implements Processor {
    private static final Logger LOG = LoggerFactory.getLogger(DataProcessor.class.getName());
    private final OptionHolder optionHolder;
    private final List<Data> dataList = new ArrayList<>();

    public DataProcessor(OptionHolder optionHolder) {
        this.optionHolder = optionHolder;
    }

    /**
     * Читает входные файлы, фильтрует их содержимое и добавляет обработанные данные в список `dataList`.
     * Ошибки логируются, но обработка остальных файлов продолжается.
     */
    @Override
    public void process() {
        Filter filter = new DataFilter();
        DataReader reader = new DataReader();
        Data data;
        for (String file : optionHolder.getInputFiles()) {
            data = new Data();
            Path filePath = Path.of(file);
            if (!Files.exists(filePath)) {
                LOG.error("Файл {} не существует", filePath);
                continue;
            }
            boolean hasData = reader.read(filter, data, filePath);
            if (hasData) {
                dataList.add(data);
            }
        }
    }

    @Override
    public List<Data> getData() {
        return dataList;
    }
}
