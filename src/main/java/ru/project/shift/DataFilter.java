package ru.project.shift;

public class DataFilter implements Filter {
    @Override
    public void filter(Data data, String line) {
        if (line.matches("^-?\\d+$")) {
            data.addInteger(Long.parseLong(line));
        } else if (line.matches("^-?\\d*\\.\\d+(E-?\\d+)?$")) {
            data.addDouble(Double.parseDouble(line));
        } else if (line.matches("^[a-zA-Zа-яА-Я\\s]+$")) {
            data.addString(line);
        }
    }
}
