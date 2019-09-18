package io.riguron.metro.application.frontend.internal;

import lombok.Getter;
import io.riguron.metro.model.Station;

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
