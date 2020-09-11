package com.ea.festival.details;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecordLabel {
    private String labelName;
    private List<Band> bands;

}
