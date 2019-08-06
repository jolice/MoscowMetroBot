package me.nextgeneric.metro.route;

import me.nextgeneric.metro.graph.api.Indexed;

import java.util.List;

public interface RoutePlanner<T extends Indexed> {

    List<T> route(Indexed from, Indexed to);
}
