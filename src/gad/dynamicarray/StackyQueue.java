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
        return first.size() + second.size();
    }

    @Override
    public void pushBack(int value) {
        first.pushBack(value);
    }

    @Override
    public int popFront() {
       if (second.isEmpty()){
           while (!first.isEmpty()){
               second.pushBack(first.popBack());
           }
       }
        return second.popBack();
    }

    @Override
    public String toString() {
        return first + ", " + second;
    }
}