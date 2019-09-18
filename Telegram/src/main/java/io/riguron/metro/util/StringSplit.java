package io.riguron.metro.util;

public class StringSplit {

    private String[] array;
    private String source;

    public StringSplit(String source) {
        this.source = source;
    }

    public int split(String regex) {
        this.array = this.source.split(regex);
        return this.size();
    }

    public String get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return this.array[index];
    }

    public int size() {
        return this.array.length;
    }
}
