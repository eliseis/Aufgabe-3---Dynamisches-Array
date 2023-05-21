package gad.dynamicarray;

public class DynamicStack implements Stack {
    private DynamicArray array;
    private Result result;
    private int maxOverhead;
    private int growthFactor;

    private Interval interval;

    public DynamicStack(int growthFactor, int maxOverhead, Result result) {
        this.interval = Interval.EmptyInterval.getEmptyInterval();
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
        this.array = new DynamicArray(growthFactor,maxOverhead);
        this.result = result;
    }

    @Override
    public int size() {
        return interval.getSize(array.getLength());
    }

    @Override
    public void pushBack(int value) {
        if (array.getLength() == 0){
            interval = array.reportUsage(interval, 1);
        }
        else {
            interval = array.reportUsage(interval, interval.getSize(array.getLength()) + 1);
        }
        if (interval.isEmpty()){
            interval = new Interval.NonEmptyInterval(0,0);
        }
        else{
            interval = new Interval.NonEmptyInterval(interval.getFrom(), interval.getTo() + 1);
        }
        array.set(interval.getTo(), value);
        array.reportArray(result);
    }

    @Override
    public int popBack() {
        int last = interval.getTo();
        if (interval.getSize(array.getLength()) == 1 || interval.getSize(array.getLength()) == 0){
            interval = Interval.EmptyInterval.getEmptyInterval();
            interval = array.reportUsage(interval, 0);
        }
        else {
            interval = new Interval.NonEmptyInterval(interval.getFrom(), interval.getTo() - 1);
            interval = array.reportUsage(interval, interval.getSize(array.getLength()) - 1);
        }
        array.reportArray(result);
        return array.get(last);
    }

    @Override
    public String toString() {
        return array + ", length: " + size();
    }

}