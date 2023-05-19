package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;
    private int maxOverhead;
    private int growthFactor;

    public DynamicArray(int growthFactor, int maxOverhead) {
        if (maxOverhead <= 1 || growthFactor <= 1 || ) throw new IllegalArgumentException();
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {

        return usage;
    }

    public int get(int index) {

        return elements[index];
    }

    public void set(int index, int value) {
        elements[index] = value;
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}