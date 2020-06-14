package com.github.jolice.metro.application.frontend.internal;

import lombok.Getter;
import com.github.jolice.metro.model.Station;

@Getter
public class SingleResult implements Describable {

    private String description;
    private boolean present;
    private Station station;

    public SingleResult(String description) {
        this.description = description;
    }

    public SingleResult(Station station) {
        this.station = station;
        this.present = true;
    }


    @Override
    public String getErrorDescription() {
        return description;
    }
}
