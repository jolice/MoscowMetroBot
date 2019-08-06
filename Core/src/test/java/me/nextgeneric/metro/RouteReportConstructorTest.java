package me.nextgeneric.metro;

import me.nextgeneric.metro.graph.PlainAdjacencyMatrix;
import me.nextgeneric.metro.graph.WeightedGraph;
import me.nextgeneric.metro.graph.api.Graph;
import me.nextgeneric.metro.model.Station;
import me.nextgeneric.metro.route.report.RouteReport;
import me.nextgeneric.metro.route.report.RouteReportConstructor;
import me.nextgeneric.metro.route.report.part.RoutePart;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("NonAsciiCharacters")
class RouteReportConstructorTest {

    private static final int DISTANCE = 3;
    private static final int CHANGE_DISTANCE = 2;

    private Graph<Station> graph = new WeightedGraph<>(new PlainAdjacencyMatrix(100));
    private RouteReportConstructor routeReportConstructor = new RouteReportConstructor(graph);



    private void assertGeneral(RouteReport routeReport, int changes, int stations, int totalTime, int routeParts) {
        assertEquals(routeReport.getChanges(), changes);
        assertEquals(routeReport.getStations(), stations);
        assertEquals(routeReport.getTime(), totalTime);
        assertEquals(routeReport.getRouteParts().size(), routeParts);
    }

    private void assertRoute(RouteReport routeReport, List<RoutePart> predicates) {
        assertEquals(routeReport.getRouteParts().size(), predicates.size());
        List<RoutePart> routeParts = routeReport.getRouteParts();
        for (int i = 0; i < predicates.size(); i++) {
            RoutePart routePart = routeParts.get(i);
            assertEquals(predicates.get(i), routePart);
        }
    }

    private RouteReportConstructorTest addSubway(Station from, Station to) {
        return add(from, to, DISTANCE);
    }

    private RouteReportConstructorTest addChange(Station from, Station to) {
        return add(from, to, CHANGE_DISTANCE);
    }

    private RouteReportConstructorTest add(Station from, Station to, int distance) {
        graph.addEdge(from, to, distance);
        return this;
    }


}