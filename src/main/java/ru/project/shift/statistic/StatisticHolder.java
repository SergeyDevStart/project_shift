package ru.project.shift.statistic;

public class StatisticHolder {
    private long count;
    private double min;
    private double max;
    private double avg;
    private double sum;

    public StatisticHolder() {

    }

    public StatisticHolder(long count) {
        this.count = count;
    }

    public StatisticHolder(long count, double min, double max) {
        this.count = count;
        this.min = min;
        this.max = max;
    }

    public StatisticHolder(long count, double min, double max, double avg, double sum) {
        this.count = count;
        this.min = min;
        this.max = max;
        this.avg = avg;
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
