package ru.project.shift.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.shift.model.Data;
import ru.project.shift.model.OptionHolder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DataWriter implements Writer {
    private static final Logger LOG = LoggerFactory.getLogger(DataWriter.class.getName());
    private final List<Data> dataList;
    private final OptionHolder optionHolder;

    public DataWriter(List<Data> dataList, OptionHolder optionHolder) {
        this.dataList = dataList;
        this.optionHolder = optionHolder;
    }

    @Override
    public void writeToFile() {
        dataDistribution(dataList, optionHolder);
    }

    private void dataDistribution(List<Data> dataList, OptionHolder optionHolder) {
        for (Data data : dataList) {
            if (data.getIntegers().size() != 0) {
                writeOneDataType(optionHolder.getPrefix() + "integers.txt", data.getIntegers());
            }
            if (data.getDoubles().size() != 0) {
                writeOneDataType(optionHolder.getPrefix() + "floats.txt", data.getDoubles());
            }
            if (data.getStrings().size() != 0) {
                writeOneDataType(optionHolder.getPrefix() + "strings.txt", data.getStrings());
            }
        }
    }

    /**
     * Формирует путь к файлу
     * Проверяет существование файла. Если файл отсутствует, создаёт директории и сам файл.
     * Если `appendMode == true`, данные дописываются в файл
     * Если `appendMode == false`, файл перезаписывается
     * Ошибки логируются, но выполнение продолжается.
     *
     * @param fileName имя выходного файла
     * @param data список данных для записи
     */
    private void writeOneDataType(String fileName, List<?> data) {
        Path filePath = FileSystems.getDefault().getPath(optionHolder.getOutputPath(), fileName);
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            LOG.error("Ошибка создания файла");
        }
        StandardOpenOption openOption = optionHolder.isAppendMode()
                ? StandardOpenOption.APPEND
                : StandardOpenOption.TRUNCATE_EXISTING;
        try (var writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, openOption)) {
            for (Object line : data) {
                writer.write(line.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            LOG.error(String.format("Ошибка записи в файл: %s", filePath), e);
        }
    }
}
