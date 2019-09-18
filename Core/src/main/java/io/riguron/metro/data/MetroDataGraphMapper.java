package io.riguron.metro.data;

import io.riguron.metro.graph.api.Graph;
import io.riguron.metro.graph.api.Vertex;
import io.riguron.metro.model.MetroData;
import io.riguron.metro.graph.api.Graph;
import io.riguron.metro.graph.api.Vertex;
import io.riguron.metro.model.MetroData;
import io.riguron.metro.model.Station;
import io.riguron.metro.graph.api.Graph;
import io.riguron.metro.graph.api.Vertex;
import io.riguron.metro.model.MetroData;
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
