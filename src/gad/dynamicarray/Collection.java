package gad.dynamicarray;

public interface Collection {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

}