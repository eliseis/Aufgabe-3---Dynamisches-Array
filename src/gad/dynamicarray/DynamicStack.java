package gad.dynamicarray;

public class DynamicStack implements Stack {
    private DynamicArray array;
    private Result result;

    public DynamicStack(int growthFactor, int maxOverhead, Result result) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void pushBack(int value) {

    }

    @Override
    public int popBack() {
        return 0;
    }

    @Override
    public String toString() {
        return array + ", length: " + size();
    }

}