package me.riguron.metro.application.frontend.internal;

import lombok.Getter;

@Getter
public class Result implements Describable{

    private boolean present;
    private StationPair stationPair;

    private String description;

    public Result(StationPair stationPair) {
        this.stationPair = stationPair;
        this.present = true;
    }

    public Result(String description) {
        this.description = description;
    }

    @Override
    public String getErrorDescription() {
        return description;
    }
}
