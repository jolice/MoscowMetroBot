package me.riguron.metro;


import me.riguron.metro.configuration.JsonConfiguration;
import me.riguron.metro.data.MetroDataGraphMapper;
import me.riguron.metro.data.StationNameConflictFix;
import me.riguron.metro.data.deserializer.PlainDeserializer;
import me.riguron.metro.data.loader.JsonFileDeserializer;
import me.riguron.metro.graph.DijkstraRoutePlanner;
import me.riguron.metro.graph.PlainAdjacencyMatrix;
import me.riguron.metro.graph.WeightedGraph;
import me.riguron.metro.graph.api.Graph;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.model.Station;
import me.riguron.metro.route.RoutePlanner;
import me.riguron.metro.route.report.RouteReport;
import me.riguron.metro.route.report.RouteReportConstructor;
import me.riguron.metro.route.report.part.RoutePart;
import me.riguron.metro.service.station.StationService;
import me.riguron.metro.data.StationNameConflictFix;
import me.riguron.metro.graph.DijkstraRoutePlanner;
import me.riguron.metro.graph.WeightedGraph;
import me.riguron.metro.graph.api.Graph;
import me.riguron.metro.route.RoutePlanner;
import me.riguron.metro.route.report.RouteReport;
import me.riguron.metro.route.report.part.RoutePart;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class StationProjectionTest {

    @Test
    public void doTest() throws IOException {

        StationService stationService = new StationService();
        MetroData metroData = new JsonFileDeserializer<>(new PlainDeserializer<>(MetroData.class, new JsonConfiguration().gson()), new ClassPathResource("moscow.json")).deserialize();
        Graph<Station> graph = new WeightedGraph<>(new PlainAdjacencyMatrix(metroData.getStations().size()));

        new StationNameConflictFix().apply(metroData);
        MetroDataGraphMapper metroDataGraphMapper = new MetroDataGraphMapper(metroData, graph);
        metroDataGraphMapper.map();


        metroData.getStations().values().forEach(stationService::addStation);


        RoutePlanner<Station> routePlanner = new DijkstraRoutePlanner<>(graph);
        List<Station> route = routePlanner.route(
                stationService.getStation("Севастопольская").get("севастопольская"),
                stationService.getStation("Дубровка (ЛДЛ)").get("дубровка (лдл)")
        );



        RouteReportConstructor routeReportConstructor = new RouteReportConstructor(graph);
        RouteReport report = routeReportConstructor.constructReport(route);


        System.out.println("Time: " + report.getTime() / 60);
        System.out.println("Changes: " + report.getChanges());
        System.out.println("Stations: " + report.getStations());

        List<RoutePart> routeParts = report.getRouteParts();
        for (int i = 0; i < routeParts.size(); i++) {
            RoutePart routePart = routeParts.get(i);
            System.out.println(i + ":" + routePart.description());
        }
    }


}
