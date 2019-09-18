package io.riguron.metro.model;

import io.riguron.metro.model.link.Link;
import lombok.AllArgsConstructor;
import lombok.Data;
import io.riguron.metro.model.link.Link;
import io.riguron.metro.model.link.Link;

import java.util.Map;

@Data
@AllArgsConstructor
public class MetroData {

    private Map<Integer, Station> stations;
    private Map<Integer, Line> lines;
    private Map<Integer, Link> links;

}
