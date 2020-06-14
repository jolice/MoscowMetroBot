package com.github.jolice.metro.model;

import lombok.Data;
import com.github.jolice.metro.graph.api.AssignableIndexed;

@Data
public class Line implements AssignableIndexed {

    private int index;
    private String name;
    private String alias;

    @Override
    public int index() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
