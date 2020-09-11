package com.ea.festival.service;

import com.ea.festival.details.RecordLabel;
import com.ea.festival.ro.MusicFestivalRO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FestivalDetailsServiceTest {

    @Autowired
    private FestivalDetailsService festivalDetailsService;

    private MusicFestivalRO[] musicFestivalROS;

    @Before
    public  void setup() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        musicFestivalROS = mapper.readValue(new File("/Users/pavanmadhav/karuna_codebase/gopinath/festival/src/main/resources/responseData/data.json")
                , MusicFestivalRO[].class);
    }

    @Test
    public void getFestivalDetails() {
        List<RecordLabel> recordLabels =  festivalDetailsService.getFestivalDetails(musicFestivalROS);
        Assert.assertNotNull("Festival details are empty",recordLabels);
        Assert.assertTrue(!recordLabels.isEmpty());
    }

}
