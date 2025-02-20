package ru.project.shift.creator;

import org.apache.commons.cli.CommandLine;
import ru.project.shift.model.OptionHolder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OptionHolderCreator {
    public OptionHolder create(CommandLine cmd, List<String> inputFiles) {
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
        if (cmd.hasOption("s") && cmd.hasOption("f")) {
            optionHolder.setFullStats(true);
            optionHolder.setShortStats(false);
        } else if (cmd.hasOption("s")) {
            optionHolder.setShortStats(true);
        } else if (cmd.hasOption("f")) {
            optionHolder.setFullStats(true);
        }
        optionHolder.setInputFiles(inputFiles);
        return optionHolder;
    }
}
