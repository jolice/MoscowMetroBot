package me.riguron.metro.repository;

import me.riguron.metro.TestConfiguration;
import me.riguron.metro.config.PersistenceConfig;
import me.riguron.metro.entity.Route;
import me.riguron.metro.projection.StationProjection;
import me.riguron.metro.TestConfiguration;
import me.riguron.metro.config.PersistenceConfig;
import me.riguron.metro.entity.Route;
import me.riguron.metro.projection.StationProjection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class, PersistenceConfig.class})
@DataJpaTest
@TestPropertySource("classpath:test.properties")
@Transactional(propagation = Propagation.NEVER)
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Before
    public void seed() {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                Route ab = new Route("a", "b");
                Route cd = new Route("c", "d");
                Route ef = new Route("e", "f");
                Route bn = new Route("b", "n");
                Route am = new Route("a", "m");

                ab.setTimesQueried(10);
                cd.setTimesQueried(5);
                ef.setTimesQueried(3);
                bn.setTimesQueried(5);
                am.setTimesQueried(8);

                Arrays.asList(ab, cd, ef, bn, am).forEach(entityManager::persist);

            }
        });
    }

    @After
    public void cleanUp() {
      transactionTemplate.execute(new TransactionCallbackWithoutResult() {
          @Override
          protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
              entityManager.createQuery("DELETE from Route").executeUpdate();
          }
      });
    }

    @Test
    public void getTop() {



        List<StationProjection> topStations = routeRepository.getTopStations();

        assertEquals(18, topStations.get(0).getQueries());
        assertEquals("a", topStations.get(0).getName());


        assertEquals(15, topStations.get(1).getQueries());
        assertEquals("b", topStations.get(1).getName());

        assertEquals(8, topStations.get(2).getQueries());
        assertEquals("m", topStations.get(2).getName());

        assertEquals(5, topStations.size());

    }

    @Test
    public void getTimesQueriedSingleStations() {
        assertEquals(18, routeRepository.getTimesQueried("a"));
    }

    @Test
    public void getTimesQueriedForRoute() {
        assertEquals(10, routeRepository.getTimesQueried("a", "b"));
    }

    @Test
    public void getTopRoutes() {
        List<Route> topRoutes = routeRepository.getTopRoutes(PageRequest.of(0, 10));
        Route route = topRoutes.get(0);
        System.out.println(route);
        assertEquals(10, route.getTimesQueried());
        assertEquals("a", route.getFromStation());
        assertEquals("b", route.getToStation());
    }
}