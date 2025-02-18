package ru.project.shift;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
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
            try (var reader = Files.newBufferedReader(Paths.get(file))) {
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
