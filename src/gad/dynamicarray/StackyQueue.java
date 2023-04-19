package gad.dynamicarray;

public class StackyQueue implements Queue {
    private DynamicStack first;
    private DynamicStack second;

    public StackyQueue(int growthFactor, int maxOverhead, Result firstResult, Result secondResult) {
        first = new DynamicStack(growthFactor, maxOverhead, firstResult);
        second = new DynamicStack(growthFactor, maxOverhead, secondResult);
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
        return first + ", " + second;
    }
}