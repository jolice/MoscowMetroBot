package com.github.jolice.metro.model;

import com.github.jolice.metro.model.link.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class MetroData {

    private Map<Integer, Station> stations;
    private Map<Integer, Line> lines;
    private Map<Integer, Link> links;

}
