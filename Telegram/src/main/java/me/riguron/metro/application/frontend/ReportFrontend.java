package me.riguron.metro.application.frontend;

import me.riguron.metro.application.frontend.internal.StationPair;
import me.riguron.metro.route.report.RouteReport;
import me.riguron.metro.route.report.part.RoutePart;
import me.riguron.metro.application.frontend.internal.StationPair;
import me.riguron.metro.route.report.RouteReport;
import me.riguron.metro.route.report.part.RoutePart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportFrontend {

    public String report(StationPair stationPair, RouteReport report) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Route: ").append(stationPair.getLeft()).append(" - ").append(stationPair.getRight()).append(System.lineSeparator());
        reportBuilder.append("Stations: ").append(report.getStations()).append(System.lineSeparator());
        reportBuilder.append("Total time: ").append(report.getTime()).append(" ").append("мин").append(System.lineSeparator());
        reportBuilder.append("Changes: ").append(report.getChanges()).append(System.lineSeparator()).append(System.lineSeparator());
        reportBuilder.append("Detailed route:").append(System.lineSeparator()).append(System.lineSeparator());
        List<RoutePart> routeParts = report.getRouteParts();
        for (int i = 0; i < routeParts.size(); i++) {
            RoutePart routePart = routeParts.get(i);
            reportBuilder.append(i + 1).append(".").append(" ").append(routePart.description()).append(System.lineSeparator()).append(System.lineSeparator());
        }
        return reportBuilder.toString();

    }


}
