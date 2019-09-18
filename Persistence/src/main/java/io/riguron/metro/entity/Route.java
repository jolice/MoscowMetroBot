package io.riguron.metro.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "fromStation")
    private String fromStation;

    @NonNull
    @Column(name = "toStation")
    private String toStation;

    @Column(name = "queried")
    private int timesQueried;

    @Version
    private int version;


}
