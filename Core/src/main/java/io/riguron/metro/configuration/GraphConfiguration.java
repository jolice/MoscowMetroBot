package io.riguron.metro.configuration;

import io.riguron.metro.graph.PlainAdjacencyMatrix;
import io.riguron.metro.graph.WeightedGraph;
import io.riguron.metro.graph.api.Graph;
import io.riguron.metro.data.MetroDataGraphMapper;
import io.riguron.metro.graph.PlainAdjacencyMatrix;
import io.riguron.metro.graph.WeightedGraph;
import io.riguron.metro.graph.api.Graph;
import io.riguron.metro.model.MetroData;
import io.riguron.metro.model.Station;
import io.riguron.metro.graph.PlainAdjacencyMatrix;
import io.riguron.metro.graph.WeightedGraph;
import io.riguron.metro.graph.api.Graph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphConfiguration {

    @Bean
    public Graph<Station> graph(MetroData metroData) {
        Graph<Station> graph = new WeightedGraph<>(new PlainAdjacencyMatrix(metroData.getStations().size()));
        MetroDataGraphMapper metroDataGraphMapper = new MetroDataGraphMapper(metroData, graph);
        metroDataGraphMapper.map();
        return graph;
    }


}
