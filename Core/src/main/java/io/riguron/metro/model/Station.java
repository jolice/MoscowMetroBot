package io.riguron.metro.model;

import io.riguron.metro.graph.api.AssignableIndexed;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.riguron.metro.graph.api.AssignableIndexed;
import io.riguron.metro.graph.api.AssignableIndexed;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Station implements AssignableIndexed {

    private String name;
    private int id;
    private int lineId;
    private Set<Integer> linkIds;
    private Set<Integer> transfers;
    private boolean transferStation;

    public Station(String name, int lineId, Set<Integer> linkIds, Set<Integer> transfers, boolean transferStation) {
        this.name = name;
        this.lineId = lineId;
        this.linkIds = linkIds;
        this.transfers = transfers;
        this.transferStation = transferStation;
    }

    public boolean precedes(Station next) {
        return !(transferStation && transfers.contains(next.index()));
    }

    @Override
    public int index() {
        return id;
    }

    @Override
    public void setIndex(int index) {
        this.id = index;
    }

    @Override
    public String toString() {
        return name;
    }


}
