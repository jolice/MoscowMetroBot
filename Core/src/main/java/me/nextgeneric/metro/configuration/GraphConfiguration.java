package me.nextgeneric.metro.configuration;

import me.nextgeneric.metro.data.MetroDataGraphMapper;
import me.nextgeneric.metro.graph.PlainAdjacencyMatrix;
import me.nextgeneric.metro.graph.WeightedGraph;
import me.nextgeneric.metro.graph.api.Graph;
import me.nextgeneric.metro.model.MetroData;
import me.nextgeneric.metro.model.Station;
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
