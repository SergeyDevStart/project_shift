package ru.project.shift;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CmdParser {
    private static final Logger LOG = LoggerFactory.getLogger(CmdParser.class.getName());
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
        if (inputFiles.size() == 0) {
            LOG.error("Необходимо указать входные файлы.");
        }
        //todo добавить обработку формата файла
        return createOptionHolder(cmd, inputFiles);
    }

    private OptionHolder createOptionHolder(CommandLine cmd, List<String> inputFiles) {
        OptionHolder optionHolder = new OptionHolder();
        optionHolder.setAppendMode(cmd.hasOption("a"));
        optionHolder.setPrefix(cmd.getOptionValue("p", ""));
        String dir = cmd.getOptionValue("o");
        if (dir == null || dir.isEmpty()) {
            optionHolder.setOutputPath(System.getProperty("user.dir"));
        } else {
            Path path = Paths.get(dir).toAbsolutePath();
            optionHolder.setOutputPath(path.toString());
        }
        optionHolder.setShortStats(cmd.hasOption("s"));
        optionHolder.setFullStats(cmd.hasOption("f"));
        optionHolder.setInputFiles(inputFiles);
        return optionHolder;
    }

    public void printHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Используйте команду для запуска: java -jar app.jar [OPTIONS] <files...>", options);
    }
}
