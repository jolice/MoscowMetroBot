package me.nextgeneric.metro.service.route;

import me.nextgeneric.metro.model.Station;
import me.nextgeneric.metro.route.RoutePlanner;
import me.nextgeneric.metro.route.report.RouteReport;
import me.nextgeneric.metro.route.report.RouteReportConstructor;
import me.nextgeneric.metro.entity.Route;
import me.nextgeneric.metro.projection.StationProjection;
import me.nextgeneric.metro.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RouteService {

    private RouteReportConstructor routeReportConstructor;

    private RoutePlanner<Station> routePlanner;

    private RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteReportConstructor routeReportConstructor, RoutePlanner<Station> routePlanner, RouteRepository routeRepository) {
        this.routeReportConstructor = routeReportConstructor;
        this.routePlanner = routePlanner;
        this.routeRepository = routeRepository;
    }

    public RouteReport route(Station from, Station to) {
        this.routeRepository.updateStatistics(from.getName(), to.getName());
        return routeReportConstructor.constructReport(routePlanner.route(from, to));
    }

    public List<Route> getTopRoutes(int count) {
        return routeRepository.getTopRoutes(PageRequest.of(0, count));
    }


    public List<StationProjection> getTopStations() {
        return routeRepository.getTopStations();
    }


    public int getRouteQueries(Station from, Station to) {
        return routeRepository.getTimesQueried(from.getName(), to.getName());
    }


    public int getStationQueries(Station from) {
        return routeRepository.getTimesQueried(from.getName());
    }


}
