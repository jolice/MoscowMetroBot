package me.riguron.metro.application.frontend;

import me.riguron.metro.application.frontend.internal.Result;
import me.riguron.metro.application.frontend.internal.SingleResult;
import me.riguron.metro.application.frontend.internal.StationPair;
import me.riguron.metro.model.Station;
import me.riguron.metro.service.station.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.SortedMap;

@Component
public class StationInputParser {

    private StationService stationService;

    @Autowired
    public StationInputParser(StationService stationService) {
        this.stationService = stationService;
    }

    public SingleResult parseOne(String left) {
        SingleResult singleResult = parseInternal(left);
        return singleResult.isPresent() ? singleResult : new SingleResult(this.failParsingAll(singleResult));
    }

    public Result parsePair(String left, String right) {
        SingleResult leftResult = parseInternal(left);
        SingleResult rightResult = parseInternal(right);
        if (leftResult.isPresent() && rightResult.isPresent()) {
            return new Result(new StationPair(leftResult.getStation(), rightResult.getStation()));
        } else {
            return new Result(failParsingAll(leftResult, rightResult));
        }
    }

    private SingleResult parseInternal(String query) {
        query = query.trim();
        SortedMap<String, Station> map = stationService.getStation(query);
        if (map.isEmpty()) {
            return new SingleResult(String.format("No stations matching '%s'", query));
        } else {
            if (map.size() > 1) {
                StringBuilder response = new StringBuilder();
                map.forEach((s, station) -> response.append("- ").append(station.getName()).append(System.lineSeparator()));
                return new SingleResult(String.format("There are multiple stations matching '%s': %s%s%s", query, System.lineSeparator(),
                        System.lineSeparator(), response.toString()));
            } else {
                return new SingleResult(map.values().iterator().next());
            }
        }
    }

    private String failParsingAll(SingleResult... results) {
        StringBuilder resultString = new StringBuilder("The following errors have occurred while processing your request:").append(System.lineSeparator()).append(System.lineSeparator());
        int errors = 0;
        for (SingleResult result : results) {
            if (!result.isPresent()) {
                resultString.append(++errors).append(". ").append(result.getDescription()).append(System.lineSeparator());
            }
        }
        return resultString.toString();
    }


}
