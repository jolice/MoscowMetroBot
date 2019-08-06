package me.nextgeneric.metro.route.report.part;

import me.nextgeneric.metro.model.Station;

public class Subway extends RoutePart {

    public Subway(Station from, Station to, int distance) {
        super(from, to, distance);
    }

    @Override
    public String description() {
        return String.format("Move from station %s to station %s without changes (%d min)", getFrom(), getTo(), getDistance() / 60);
    }
}
