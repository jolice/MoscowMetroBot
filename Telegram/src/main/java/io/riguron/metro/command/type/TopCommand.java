package io.riguron.metro.command.type;

import io.riguron.metro.command.Command;
import io.riguron.metro.command.arguments.Arguments;
import io.riguron.metro.service.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@Component
public class TopCommand implements Command {

    private RouteService routeService;

    @Autowired
    public TopCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(Arguments args) {
        if (args.length() == 0)
            return top(routeService.getTopRoutes(10), route -> route.getFromStation() + " - " + route.getToStation() + " (" + route.getTimesQueried() + ") searches");
        else
            return top(routeService.getTopStations(), stationProjection -> stationProjection.getName() + " - " + stationProjection.getQueries() + " uses in all routes");
    }

    @Override
    public List<String> aliases() {
        return Collections.singletonList("top");
    }

    private <T> String top(List<T> list, Function<T, String> function) {
        if (list.isEmpty()) {
            return "No data available!";
        } else {
            StringBuilder top = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                T object = list.get(i);
                top.append(i + 1).append(".").append(" ").append(function.apply(object)).append(System.lineSeparator());
            }
            return top.toString();
        }

    }
}
