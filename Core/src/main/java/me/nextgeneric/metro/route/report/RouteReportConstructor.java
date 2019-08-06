package me.nextgeneric.metro.route.report;

import me.nextgeneric.metro.graph.api.Graph;
import me.nextgeneric.metro.route.report.part.Change;
import me.nextgeneric.metro.route.report.part.RoutePart;
import me.nextgeneric.metro.route.report.part.Subway;
import me.nextgeneric.metro.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteReportConstructor {

    private Graph<Station> map;

    @Autowired
    public RouteReportConstructor(Graph<Station> map) {
        this.map = map;
    }

    public RouteReport constructReport(List<Station> route) {

        int changes = 0, totalTime = 0, partTime = 0;

        List<RoutePart> parts = new ArrayList<>();
        if (!route.isEmpty()) {
            Station from = route.get(0);
            for (int i = 0; i < route.size(); i++) {

                Station current = route.get(i);
                Station next = i + 1 != route.size() ? route.get(i + 1) : current;

                int distance = map.getEdge(current, next);
                totalTime += distance;
                partTime = isChangeForPair(current, next) ? partTime : partTime + distance;

                if (isChangeForSubwayFix(route, i, from, next)) {
                    parts.add(new Subway(from, current, partTime));
                    partTime = 0;
                }

                if (isChangeForPair(current, next)) {
                    parts.add(new Change(current, next, map.getEdge(current, next)));
                    changes++;
                    from = next;
                }
            }
        }
        return new RouteReport(parts, totalTime, changes, route.size());
    }

    private boolean isChangeForSubwayFix(List<Station> route, int i, Station from, Station next) {
        Station current = route.get(i);
        Station previous = i > 0 ? route.get(i - 1) : current;
        return !from.equals(current) && (!isChangeForPair(previous, current) && isChangeForPair(current, next) || (i + 1 == route.size()));
    }

    private boolean isChangeForPair(Station from, Station to) {
        return !from.equals(to) && !(from.precedes(to));
    }


}
