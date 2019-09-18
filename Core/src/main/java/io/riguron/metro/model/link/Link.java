package io.riguron.metro.model.link;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import io.riguron.metro.graph.api.AssignableIndexed;

@Data
public class Link implements AssignableIndexed {

    @SerializedName("fromStationId")
    private int from;

    @SerializedName("toStationId")
    private int to;

    @SerializedName("weightTime")
    private int time;

    private int index;

    @Override
    public int index() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
