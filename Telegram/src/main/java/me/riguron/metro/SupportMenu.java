package me.riguron.metro;

import me.riguron.metro.model.MetroData;
import me.riguron.metro.service.station.StationService;
import me.riguron.metro.model.MetroData;
import me.riguron.metro.service.station.StationService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class SupportMenu {

    private StationService stationService;
    private MetroData metroData;
    private String support;

    @Autowired
    public SupportMenu(StationService stationService, MetroData metroData) {
        this.stationService = stationService;
        this.metroData = metroData;
    }

    @PostConstruct
    public void doLoadHelp() {

        Resource resource = new ClassPathResource("help.txt");
        try {

            StringBuilder result = new StringBuilder(
                    String.format(
                            IOUtils.readLines(resource.getInputStream(), StandardCharsets.UTF_8).stream().collect(Collectors.joining(System.lineSeparator()))
                            , stationService.getRandomStation().getName(), stationService.getRandomStation().getName())
            );


            metroData.getLines().forEach((integer, line) -> result.append(System.lineSeparator()).append(line.getName()).append(" - ").append(line.getAlias()));
            this.support = result.append(System.lineSeparator()).append(System.lineSeparator()).
                    append("To build a route between stations Курская кольцевой линии and Бауманская, you should enter the following query" +
                            " Курская (КЛ) > Бауманская").toString();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String support() {
        return support;
    }
}
