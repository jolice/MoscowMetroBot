package io.riguron.metro.repository;

import io.riguron.metro.entity.Route;
import io.riguron.metro.entity.Route;
import io.riguron.metro.projection.StationProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends Repository<Route, Long>, CustomRouteRepository {

    @Query("SELECT NEW java.lang.Integer(r.timesQueried) FROM Route r WHERE r.fromStation = :fromStation and r.toStation = :toStation")
    Integer getTimesQueried(@Param("fromStation") String fromStation, @Param("toStation") String toStation);

    @Query("SELECT r FROM Route r ORDER BY r.timesQueried DESC")
    List<Route> getTopRoutes(Pageable pageable);

    @Query(value = "SELECT station AS name, sum(queried) AS queries " +
            "FROM (SELECT from_station AS station, queried FROM route UNION SELECT to_station AS station, queried FROM route) " +
            "sub GROUP BY station HAVING sum(queried) > 0 ORDER BY queries DESC LIMIT 5", nativeQuery = true)
    List<StationProjection> getTopStations();

    @Query("SELECT SUM(r.timesQueried) FROM Route r WHERE r.fromStation = :name OR r.toStation = :name")
    Integer getTimesQueried(@Param("name") String name);
}
