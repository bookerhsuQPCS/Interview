package com.interview.demo.service;

import com.interview.demo.dto.QueryCoindeskResponse;

import io.micrometer.core.instrument.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CoindeskServiceJunitTest {
	
    @Autowired
    private CoindeskService coindeskService;
    
    @Test
    void testGetCoindeskRaw_OK() {

    	QueryCoindeskResponse response = coindeskService.getCoindeskRaw();

        assertNotNull(response);
        assertTrue(StringUtils.isNotBlank(response.getChartName()));
        assertTrue(StringUtils.isNotBlank(response.getDisclaimer()));
        assertNotNull(response.getTNtime());
        assertNotNull(response.getBpi());
        assertTrue(response.getBpi().keySet().size() > 0);

    }

    @Test
    void testGetCoindeskAndTnTime_OK() {

    	QueryCoindeskResponse response = coindeskService.getCoindeskAndTnTime();

        assertNotNull(response);
        assertTrue(StringUtils.isNotBlank(response.getChartName()));
        assertTrue(StringUtils.isNotBlank(response.getDisclaimer()));
        assertNotNull(response.getTNtime());
        assertNotNull(response.getBpi());
        assertTrue(response.getBpi().keySet().size() > 0);
    }
}
