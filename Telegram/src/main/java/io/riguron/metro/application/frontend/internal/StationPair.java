package io.riguron.metro.application.frontend.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import io.riguron.metro.model.Station;

@AllArgsConstructor
@Getter
public class StationPair {

    private Station left;
    private Station right;

}