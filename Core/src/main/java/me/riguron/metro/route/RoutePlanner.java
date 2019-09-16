package me.riguron.metro.route;

import me.riguron.metro.graph.api.Indexed;

import java.util.List;

public interface RoutePlanner<T extends Indexed> {

    List<T> route(Indexed from, Indexed to);
}
