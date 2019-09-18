package io.riguron.metro.application;

import io.riguron.metro.application.frontend.ReportFrontend;
import io.riguron.metro.application.frontend.StationInputParser;
import io.riguron.metro.application.frontend.internal.Result;
import io.riguron.metro.application.frontend.internal.StationPair;
import io.riguron.metro.message.MessageHandler;
import io.riguron.metro.service.route.RouteService;
import io.riguron.metro.service.station.StationService;
import io.riguron.metro.util.StringSplit;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("Application")
public class ApplicationMessageHandler implements MessageHandler {

    private static final String STATION_NAME_SEPARATOR = ">";

    private StationService stationService;

    private StationInputParser stationInputParser;

    private ReportFrontend reportFrontend;

    private RouteService routeService;

    public ApplicationMessageHandler(StationService stationService, StationInputParser stationInputParser, ReportFrontend reportFrontend, RouteService routeService) {
        this.stationService = stationService;
        this.stationInputParser = stationInputParser;
        this.reportFrontend = reportFrontend;
        this.routeService = routeService;
    }

    @Override
    public String handleMessage(Message message) {
        if (message.getText().isEmpty()) {
            return this.applicationUsage();
        } else {
            StringSplit stringSplit = new StringSplit(message.getText());
            if (stringSplit.split(STATION_NAME_SEPARATOR) != 2) {
                return applicationUsage();
            } else {
                Result result = stationInputParser.parsePair(stringSplit.get(0), stringSplit.get(1));
                return result.isPresent() ? report(result.getStationPair()) : result.getErrorDescription();
            }

        }
    }

    private String report(StationPair stationPair) {
        if (stationPair.getLeft().equals(stationPair.getRight())) {
            return "Please specify two distinct stations";
        } else {
            return reportFrontend.report(stationPair, routeService.route(stationPair.getLeft(), stationPair.getRight()));
        }
    }

    private String applicationUsage() {
        return "Please specify 'from' and 'to' stations, like: " + randomStations();
    }

    private String randomStations() {
        return stationService.getRandomStation().getName() + " > " + stationService.getRandomStation().getName();
    }


}