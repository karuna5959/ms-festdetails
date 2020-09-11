package com.ea.festival.ro;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MusicFestivalRO {
    private String name;
    private List<BandRO> bands;

}
