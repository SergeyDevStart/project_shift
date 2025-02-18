package ru.project.shift;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private final List<Long> integers = new ArrayList<>();
    private final List<Double> doubles = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void addInteger(Long value) {
        integers.add(value);
    }

    public void addDouble(Double value) {
        doubles.add(value);
    }

    public void addString(String value) {
        strings.add(value);
    }

    public List<Long> getIntegers() {
        return integers;
    }

    public List<Double> getDoubles() {
        return doubles;
    }

    public List<String> getStrings() {
        return strings;
    }
}
