package ru.project.shift.processor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.project.shift.model.Data;
import ru.project.shift.model.OptionHolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataProcessorTest {
    private DataProcessor dataProcessor;
    private OptionHolder optionHolder;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        optionHolder = new OptionHolder();
        dataProcessor = new DataProcessor(optionHolder);
        tempFile = Files.createTempFile("test", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void whenFileNotExistsThenNothingHappens() {
        optionHolder.setInputFiles(List.of("notExistFile.txt"));
        dataProcessor.process();
        assertTrue(dataProcessor.getData().isEmpty());
    }

    @Test
    void whenProcessFileThenSuccessful() throws IOException {
        Files.writeString(tempFile, "123\n10.5\nJava", StandardOpenOption.WRITE);
        optionHolder.setInputFiles(List.of(tempFile.toString()));
        optionHolder.setAppendMode(false);

        dataProcessor.process();

        List<Data> dataList = dataProcessor.getData();
        assertThat(dataList.size()).isEqualTo(1);
        Data data = dataList.get(0);
        assertThat(data.getIntegers()).isEqualTo(List.of(123L));
        assertThat(data.getDoubles()).isEqualTo(List.of(10.5));
        assertThat(data.getStrings()).isEqualTo(List.of("Java"));
    }

    @Test
    void whenProcessWithAppenModeThenGetTwoData() throws IOException {
        Files.writeString(tempFile, "123", StandardOpenOption.WRITE);
        Path tempFileTwo = Files.createTempFile("tempFileTwo", ".txt");
        Files.writeString(tempFileTwo, "Java", StandardOpenOption.WRITE);
        optionHolder.setInputFiles(List.of(tempFile.toString(), tempFileTwo.toString()));
        optionHolder.setAppendMode(true);

        dataProcessor.process();

        List<Data> dataList = dataProcessor.getData();
        assertThat(dataList.size()).isEqualTo(2);

        Files.deleteIfExists(tempFileTwo);
    }
}