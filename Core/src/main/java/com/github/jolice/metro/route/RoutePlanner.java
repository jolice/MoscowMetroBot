package com.github.jolice.metro.route;

import com.github.jolice.metro.graph.api.Indexed;

import java.util.List;

public interface RoutePlanner<T extends Indexed> {

    List<T> route(Indexed from, Indexed to);
}
