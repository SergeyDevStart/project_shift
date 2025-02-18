package ru.project.shift;

import java.util.List;

public interface Processor {
    void process();

    List<Data> getData();
}
