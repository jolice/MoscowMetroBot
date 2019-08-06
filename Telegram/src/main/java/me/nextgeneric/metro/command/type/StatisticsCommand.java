package me.nextgeneric.metro.command.type;

import me.nextgeneric.metro.application.frontend.StationInputParser;
import me.nextgeneric.metro.application.frontend.internal.Result;
import me.nextgeneric.metro.application.frontend.internal.SingleResult;
import me.nextgeneric.metro.command.Command;
import me.nextgeneric.metro.command.arguments.Arguments;
import me.nextgeneric.metro.service.route.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StatisticsCommand implements Command {

    private RouteService routeService;

    private StationInputParser stationInputParser;

    @Autowired
    public StatisticsCommand(RouteService routeService, StationInputParser stationInputParser) {
        this.routeService = routeService;
        this.stationInputParser = stationInputParser;
    }

    @Override
    public String execute(Arguments args) {
        if (args.length() > 1)
            return statsForPair(args);
        else if (args.length() == 1)
            return statsForSingle(args);
        else
            return "Please specify a station or a route!";
    }

    @Override
    public List<String> aliases() {
        return Arrays.asList("stat", "statistics");
    }

    private String statsForSingle(Arguments args) {
        SingleResult singleResult = stationInputParser.parseOne(args.getArgument(0));
        return singleResult.isPresent() ? "This station was involved in " + routeService.getStationQueries(
                singleResult.getStation()
        ) + " routes" : singleResult.getErrorDescription();
    }

    private String statsForPair(Arguments args) {
        Result result = stationInputParser.parsePair(args.getArgument(0), args.getArgument(1));
        return result.isPresent() ? "This route was built " + routeService.getRouteQueries(
                result.getStationPair().getLeft(), result.getStationPair().getRight()
        ) + "times" : result.getErrorDescription();
    }
}
