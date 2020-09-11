package com.ea.festival.service;

import com.ea.festival.details.Band;
import com.ea.festival.details.RecordLabel;
import com.ea.festival.ro.BandRO;
import com.ea.festival.ro.MusicFestivalRO;
import com.ea.festival.util.SortUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FestivalDetailsService {

    public List<RecordLabel> getFestivalDetails(MusicFestivalRO[] details) {
        //call API to get the details
        //MusicFestivalRO[] details  = restTemplate.getForObject("url", MusicFestivalRO.class);
        //Mocking to get the Details.

        List<RecordLabel> recordLabels = new ArrayList<>();
        List<MusicFestivalRO> musicalROs = Arrays.asList(details);
        musicalROs.forEach(musicalRO -> {
            musicalRO.getBands().forEach(bands -> {
                if (CollectionUtils.isEmpty(recordLabels)) {
                    getRecordLabelList(musicalRO, bands, recordLabels);
                } else {
                    boolean labelFound = false;
                    for (int i = 0; i < recordLabels.size(); i++) {
                        if (recordLabels.get(i).getLabelName().equals(bands.getRecordLabel())) {
                            labelFound = true;
                            if (labelFound) {
                                boolean bandFound = false;
                                for (int j = 0; j < recordLabels.get(i).getBands().size(); j++) {
                                    if (recordLabels.get(i).getBands().get(j).getBandName().equals(bands.getName())) {
                                        bandFound = true;
                                        recordLabels.get(i).getBands().get(j).getMusicFestivals().add(musicalRO.getName());
                                    }
                                }
                                if (!bandFound) {
                                    List<Band> bandList = recordLabels.get(i).getBands();
                                    Band band = new Band();
                                    band.setBandName(bands.getName());

                                    List<String> festivalList = new ArrayList<>();
                                    festivalList.add(musicalRO.getName());
                                    Collections.sort(festivalList);

                                    band.setMusicFestivals(festivalList);
                                    bandList.add(band);
                                }

                            }

                        }
                    }
                    if (!labelFound) {
                        getRecordLabelList(musicalRO, bands, recordLabels);
                        labelFound = false;

                    }

                }
            });
        });
        SortUtil.sortLabels(recordLabels);
        return recordLabels;
    }

    private void getRecordLabelList(MusicFestivalRO musicalRO, BandRO bands, List<RecordLabel> recordLabels) {
        RecordLabel recordLabel = new RecordLabel();
        recordLabel.setLabelName(bands.getRecordLabel());

        List<Band> bandList = new ArrayList<>();
        Band band = new Band();
        band.setBandName(bands.getName());

        List<String> festivalList = new ArrayList<>();
        festivalList.add(musicalRO.getName());
        Collections.sort(festivalList);
        band.setMusicFestivals(festivalList);
        bandList.add(band);

        recordLabel.setBands(bandList);

        recordLabels.add(recordLabel);
    }

}
