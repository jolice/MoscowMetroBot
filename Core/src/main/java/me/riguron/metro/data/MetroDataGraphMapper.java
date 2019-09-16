package me.riguron.metro.data;

import me.riguron.metro.graph.api.Graph;
import me.riguron.metro.graph.api.Vertex;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.model.Station;
import me.riguron.metro.graph.api.Graph;
import me.riguron.metro.graph.api.Vertex;
import me.riguron.metro.model.MetroData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetroDataGraphMapper {

    private MetroData metroData;
    private Graph<Station> graph;

    @Autowired
    public MetroDataGraphMapper(MetroData metroData, Graph<Station> graph) {
        this.graph = graph;
        this.metroData = metroData;
    }

    public void map() {
        metroData.getStations().values().forEach(station -> graph.addVertex(new Vertex<>(station)));
        metroData.getLinks().values().forEach(link -> {
            graph.addEdge(link::getFrom, link::getTo, link.getTime());
            graph.addEdge(link::getTo, link::getFrom, link.getTime());
        });
    }



}
