package ru.project.shift.processor;

import ru.project.shift.model.Data;

import java.util.List;

public interface Processor {
    void process();

    List<Data> getData();
}
