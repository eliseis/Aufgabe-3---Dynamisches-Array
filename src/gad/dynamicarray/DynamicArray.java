package gad.dynamicarray;

import java.util.Arrays;

public class DynamicArray {
    private int[] elements;
    private int growthFactor;
    private  int maxOverhead;
    private int w = 1;
    private int n = 0;

    public DynamicArray(int growthFactor, int maxOverhead){
        if (maxOverhead < 1 || growthFactor < 1 || growthFactor < maxOverhead) throw new IllegalArgumentException();
        this.growthFactor = growthFactor;
        this.maxOverhead = maxOverhead;
    }

    public int getLength() {
        return elements.length;
    }

    public Interval reportUsage(Interval usage, int minSize) {
        int a = usage.getTo() - usage.getFrom();
        if (minSize > elements.length){
            int[] mas = new int[elements.length * maxOverhead];
            if (a >= 0){
                for(int i = usage.getFrom(); i <= usage.getTo(); i++){
                    mas[i - usage.getFrom()] = elements[i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0,a);
            }
            else {
                for (int i = 0 ; i <= usage.getTo(); i++){
                    mas[elements.length - usage.getFrom() + i + 1] = elements[i];
                }
                for(int i = 0; i + usage.getFrom() < elements.length; i++){
                    mas[i] = elements[usage.getFrom() + i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0, elements.length - a);
            }
        }
        if(minSize * maxOverhead < elements.length ){
            int[] mas = new int[minSize * growthFactor];
            if (a >= 0){
                for(int i = usage.getFrom(); i <= usage.getTo(); i++){
                    mas[i - usage.getFrom()] = elements[i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0,a);
            }
            else {
                for (int i = 0 ; i <= usage.getTo(); i++){
                    mas[elements.length - usage.getFrom() + i + 1] = elements[i];
                }
                for(int i = 0; i + usage.getFrom() < elements.length; i++){
                    mas[i] = elements[usage.getFrom() + i];
                }
                elements = mas;
                return new Interval.NonEmptyInterval(0, elements.length - a);
            }
        }
        return null;
    }

    public int get(int index) {
        return this.elements[index];
    }

    public void set(int index, int value) {
        this.elements[index] = value;
    }

    public void reportArray(Result result) {
        result.logArray(elements);
    }

    @Override
    public String toString() {
        return Arrays.toString(elements);
    }
    void reallocate(int new_w){
        w = new_w;
        int[] new_elements = new int[new_w];
        for (int i = 0; i < n; i++){
            new_elements[i] = elements[i];
        }
        elements = new_elements;
    }
}