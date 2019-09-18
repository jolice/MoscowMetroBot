package io.riguron.metro.service.route;

import io.riguron.metro.entity.Route;
import io.riguron.metro.model.Station;
import io.riguron.metro.projection.StationProjection;
import io.riguron.metro.repository.RouteRepository;
import io.riguron.metro.route.RoutePlanner;
import io.riguron.metro.route.report.RouteReport;
import io.riguron.metro.route.report.RouteReportConstructor;
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
