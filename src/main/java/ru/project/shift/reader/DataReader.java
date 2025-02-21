package ru.project.shift.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.project.shift.filter.Filter;
import ru.project.shift.model.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataReader {
    private static final Logger LOG = LoggerFactory.getLogger(DataReader.class.getName());

    /**
     * Читает строки из файла, применяет фильтр и записывает обработанные данные в объект `Data`.
     * Ошибки ввода/вывода логируются, но выполнение программы продолжается.
     *
     * @param filter  фильтр для обработки данных
     * @param data    объект `Data`, в который записываются обработанные строки
     * @param filePath путь к файлу для чтения
     * @return `true`, если файл содержит данные, иначе `false`
     */
    public boolean read(Filter filter, Data data, Path filePath) {
        boolean hasData = false;
        try (var reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                filter.filter(data, line.trim());
                hasData = true;
            }
        } catch (IOException e) {
            LOG.error("Ошибка чтения файла: {}", filePath, e);
        }
        return hasData;
    }
}
