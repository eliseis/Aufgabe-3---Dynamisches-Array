package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;
    private int maxOverhead;
    private int growthFactor;

    public DynamicArray(int growthFactor, int maxOverhead) {
        if (maxOverhead < 1 || growthFactor < 1 || maxOverhead < growthFactor ) throw new IllegalArgumentException();
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
        this.elements = new int[0];
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
            if (mas.length == 0){
                elements = mas;
                return Interval.EmptyInterval.getEmptyInterval();
            }
            if (usage.getFrom() <= usage.getTo()){
                for(int i = usage.getFrom(); i <= usage.getTo(); i++){
                    mas[i - usage.getFrom()] = elements[i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0,usage.getSize(elements.length) - 1);
            }
            else {
                for(int i = 0; i <= elements.length - usage.getFrom() - 1; i++){
                    mas[i] = elements[usage.getFrom() + i];
                }
                for (int i = elements.length - usage.getFrom() ; i <= usage.getSize(elements.length) - 1; i++){
                    mas[i] = elements[i + usage.getFrom() - elements.length];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0,usage.getSize(elements.length) - 1);
            }
        }
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