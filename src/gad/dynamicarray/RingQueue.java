package gad.dynamicarray;

public class RingQueue implements Queue {
    private DynamicArray array;
    private Result result;
    private int maxOverhead;
    private int growthFactor;

    private Interval interval;

    public RingQueue(int growthFactor, int maxOverhead, Result result) {
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
        if (interval.isEmpty()){
            interval = new Interval.NonEmptyInterval(0,0);
            array.set(interval.getTo(),value);
            array.reportArray(result);
            return;
        }
        if (interval.getSize(array.getLength()) >= array.getLength()) {
            interval = array.reportUsage(interval,array.getLength() + 1);
            interval = new Interval.NonEmptyInterval(interval.getFrom(), (interval.getTo() + 1) % array.getLength());
        } else {
            interval = new Interval.NonEmptyInterval(interval.getFrom(), interval.getTo() + 1);
        }
        array.set(interval.getTo(), value);
        array.reportArray(result);
    }

    @Override
    public int popFront() {
        int last = array.get(interval.getFrom());
        if (interval.getSize(array.getLength()) == 1 || interval.getSize(array.getLength()) == 0){
            interval = array.reportUsage(Interval.EmptyInterval.getEmptyInterval(), 0);
        }
        else {
            interval = array.reportUsage(new Interval.NonEmptyInterval(interval.getFrom() + 1, interval.getTo()), interval.getSize(array.getLength()));
        }
        array.reportArray(result);
        return last;
    }

    @Override
    public String toString() {
        return array + ", size: " + size();
    }
}