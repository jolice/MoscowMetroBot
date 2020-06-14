package com.github.jolice.metro.application.frontend.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.github.jolice.metro.model.Station;

@AllArgsConstructor
@Getter
public class StationPair {

    private Station left;
    private Station right;

}