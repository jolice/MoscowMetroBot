package io.riguron.metro.route.report.part;

import io.riguron.metro.model.Station;
import io.riguron.metro.model.Station;
import io.riguron.metro.model.Station;

public class Change extends RoutePart {

    public Change(Station from, Station to, int distance) {
        super(from, to, distance);
    }

    @Override
    public String description() {
        return String.format("Change from station %s to station %s (%d мин)", getFrom(), getTo(), getDistance() / 60);
    }

}
