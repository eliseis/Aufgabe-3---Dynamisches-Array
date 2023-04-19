package gad.dynamicarray;

public class RingQueue implements Queue {
    private DynamicArray array;
    private Result result;

    public RingQueue(int growthFactor, int maxOverhead, Result result) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void pushBack(int value) {

    }

    @Override
    public int popFront() {
        return 0;
    }

    @Override
    public String toString() {
        return array + ", size: " + size();
    }
}