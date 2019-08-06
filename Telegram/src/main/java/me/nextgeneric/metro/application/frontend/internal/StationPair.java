package me.nextgeneric.metro.application.frontend.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.nextgeneric.metro.model.Station;

@AllArgsConstructor
@Getter
public class StationPair {

    private Station left;
    private Station right;

}