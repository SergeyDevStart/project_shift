package ru.project.shift.parser;

import org.apache.commons.cli.*;
import ru.project.shift.creator.OptionHolderCreator;
import ru.project.shift.model.OptionHolder;

import java.util.Arrays;
import java.util.List;

public class CmdParser {
    private final Options options;

    public CmdParser() {
        this.options = new Options();
        init();
    }

    private void init() {
        options.addOption("a", false, "Добавлять в существующие файлы (по умолчанию откл.)");
        options.addOption("p", true, "Указать префикс для результирующих файлов");
        options.addOption("o", true, "Указать путь для результирующих файлов");
        options.addOption("s", false, "Вывести краткую статистику");
        options.addOption("f", false, "Вывести полную статистику");
        options.addOption("h", false, "Справка");
    }

    public OptionHolder parsingArguments(String[] args) throws ParseException, IllegalArgumentException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        List<String> inputFiles = Arrays.asList(cmd.getArgs());
        if (cmd.hasOption("h")) {
            printHelp();
            System.exit(0);
        }
        if (inputFiles.size() == 0) {
            throw new IllegalArgumentException("Необходимо указать входные файлы.");
        }
        for (String fileName : inputFiles) {
            if (!fileName.matches("^[а-яА-Яa-zA-Z0-9_]+\\.txt$")) {
                throw new IllegalArgumentException(
                        String.format("Используйте формат файла: %s", "FILE_NAME.txt")
                );
            }
        }
        OptionHolderCreator creator = new OptionHolderCreator();
        return creator.create(cmd, inputFiles);
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Используйте команду для запуска: java -jar app.jar [OPTIONS] <files...>", options);
    }
}
