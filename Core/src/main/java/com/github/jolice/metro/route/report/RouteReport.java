package com.github.jolice.metro.route.report;

import com.github.jolice.metro.route.report.part.RoutePart;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class RouteReport {

    private List<RoutePart> routeParts;

    private int time;
    private int changes;
    private int stations;

    public int getTime() {
        return time / 60;
    }
}
