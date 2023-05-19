package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;
    private int maxOverhead;
    private int growthFactor;

    public DynamicArray(int growthFactor, int maxOverhead) {
        if (maxOverhead <= 1 || growthFactor <= 1) throw new IllegalArgumentException();
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {

        if (minSize > elements.length || minSize * maxOverhead < elements.length ){
            int[] mas = new int[minSize * growthFactor];
            if (usage.isEmpty()){
                elements = mas;
                return Interval.EmptyInterval.getEmptyInterval();
            }
            if (usage.getFrom() <= usage.getTo()){
                for(int i = usage.getFrom(); i <= usage.getTo(); i++){
                    mas[i - usage.getFrom()] = elements[i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0,usage.getSize(elements.length));
            }
            else {
                for (int i = 0 ; i <= usage.getTo(); i++){
                    mas[elements.length - usage.getFrom() + i] = elements[i];
                }
                for(int i = 0; i + usage.getFrom() <= elements.length - 1; i++){
                    mas[i] = elements[usage.getFrom() + i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0, elements.length - usage.getSize(elements.length) - 3);
            }
        }
        return usage;
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