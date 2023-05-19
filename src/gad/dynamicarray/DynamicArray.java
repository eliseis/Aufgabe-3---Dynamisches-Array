package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;

    public DynamicArray(int growthFactor, int maxOverhead) {
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {
        return null;
    }

    public int get(int index) {
        return 0;
    }

    public void set(int index, int value) {
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
}