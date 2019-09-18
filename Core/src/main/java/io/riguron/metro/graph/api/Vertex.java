package io.riguron.metro.graph.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
public class Vertex<T extends Indexed> {

    @Setter(AccessLevel.NONE)
    @NonNull
    private T item;

    private boolean visited;

}
