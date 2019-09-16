package me.riguron.metro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.riguron.metro.model.link.Link;
import me.riguron.metro.model.link.Link;

import java.util.Map;

@Data
@AllArgsConstructor
public class MetroData {

    private Map<Integer, Station> stations;
    private Map<Integer, Line> lines;
    private Map<Integer, Link> links;

}
