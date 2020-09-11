package com.ea.festival.controller;


import com.ea.festival.details.RecordLabel;
import com.ea.festival.ro.MusicFestivalRO;
import com.ea.festival.service.FestivalDetailsService;
import com.ea.festival.util.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.List;

@RestController
public class FestivalDetailsController {

    @Autowired
    FestivalDetailsService festivalDetailsService;

    @RequestMapping(value = "/getFestivalDetails", method = RequestMethod.GET)
    public List<RecordLabel> getFestivalDetails() {
        try {
            MusicFestivalRO[] details = SortUtil.getMusicDetails();
            return festivalDetailsService.getFestivalDetails(details);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
