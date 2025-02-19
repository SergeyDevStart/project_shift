package ru.project.shift.filter;

import ru.project.shift.model.Data;

public interface Filter {
    void filter(Data data, String line);
}
