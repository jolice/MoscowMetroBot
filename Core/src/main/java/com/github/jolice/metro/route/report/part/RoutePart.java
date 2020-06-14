package com.github.jolice.metro.route.report.part;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import com.github.jolice.metro.model.Station;

@AllArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public abstract class RoutePart {

    private Station from;
    private Station to;
    private int distance;

    public abstract String description();

    @Override
    public String toString() {
        return description();
    }

}
