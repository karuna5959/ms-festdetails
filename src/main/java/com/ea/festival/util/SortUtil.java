package com.ea.festival.util;

import com.ea.festival.details.Band;
import com.ea.festival.details.RecordLabel;
import com.ea.festival.ro.MusicFestivalRO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SortUtil {

    public static void sortLabels(List<RecordLabel> recordLabels) {
        recordLabels.sort((RecordLabel r1, RecordLabel r2) ->
                r1.getLabelName().compareTo(r2.getLabelName()));
        recordLabels.forEach(recordLabel -> {
            recordLabel.getBands().sort((Band b1, Band b2) ->
            b1.getBandName().compareTo(b2.getBandName()));
        });
    }


    public static MusicFestivalRO[] getMusicDetails() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

         return mapper.readValue(new File("/Users/pavanmadhav/karuna_codebase/gopinath/festival/src/main/resources/responseData/data.json")
                , MusicFestivalRO[].class);
    }
}
