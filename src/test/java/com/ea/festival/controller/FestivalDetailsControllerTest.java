package com.ea.festival.controller;

import com.ea.festival.details.RecordLabel;
import com.ea.festival.service.FestivalDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FestivalDetailsControllerTest {

    @Autowired
    private FestivalDetailsController festivalDetailsController;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FestivalDetailsService festivalDetailsService;

    public FestivalDetailsControllerTest() {
    }

    @Test
    public void testGetAccountDetails() throws Exception {
       List<RecordLabel> recordLabels = new ArrayList<>();
       RecordLabel recordLabel = new RecordLabel();
       recordLabel.setLabelName("label1");
       recordLabels.add(recordLabel);
        Mockito.when(this.festivalDetailsService.getFestivalDetails(Mockito.anyObject())).thenReturn(recordLabels);
        this.mvc.perform(MockMvcRequestBuilders.get("/getFestivalDetails"));
    }

    @Test(expected = Exception.class)
    public void testGetAccountDetailsThrowsException() throws Exception {
        Mockito.when(this.festivalDetailsService.getFestivalDetails(Mockito.anyObject())).thenThrow(FileNotFoundException.class);
        this.mvc.perform(MockMvcRequestBuilders.get("/getFestivalDetails"));
    }
}
