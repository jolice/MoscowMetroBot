package me.nextgeneric.metro.repository;

import me.nextgeneric.metro.entity.Route;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRouteRepositoryDefault implements CustomRouteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateStatistics(String from, String to) {
        Route route = entityManager.createQuery("select r from Route r where r.fromStation = ?1 and r.toStation = ?2", Route.class)
                .setParameter(1, from)
                .setParameter(2, to)
                .getResultStream()
                .findFirst()
                .orElseGet(() -> new Route(from, to));
        route.setTimesQueried(route.getTimesQueried() + 1);
        entityManager.merge(route);
    }



}
