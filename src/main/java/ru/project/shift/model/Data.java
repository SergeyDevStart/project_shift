package ru.project.shift.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        return Objects.equals(integers, data.integers)
                && Objects.equals(doubles, data.doubles)
                && Objects.equals(strings, data.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(integers, doubles, strings);
    }
}
