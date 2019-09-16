package me.riguron.metro.configuration;

import me.riguron.metro.data.MetroDataGraphMapper;
import me.riguron.metro.graph.PlainAdjacencyMatrix;
import me.riguron.metro.graph.WeightedGraph;
import me.riguron.metro.graph.api.Graph;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.model.Station;
import me.riguron.metro.graph.PlainAdjacencyMatrix;
import me.riguron.metro.graph.WeightedGraph;
import me.riguron.metro.graph.api.Graph;
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
