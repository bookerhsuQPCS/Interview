package com.interview.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.controller.CurrencyController;
import com.interview.demo.dto.QueryRequest;
import com.interview.demo.dto.QueryResponse;
import com.interview.demo.entity.Currency;
import com.interview.demo.repository.CurrencyRepository;
import com.interview.demo.service.impl.CurrencyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CurrencyControllerMockMvcTest {
	
	@InjectMocks
	CurrencyController controller;

	@Mock
	CurrencyRepository dao;
	
	@Mock
	CurrencyServiceImpl service;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetCurrencyById_Found() throws Exception {
    	
        long currencyId = 1L;
        Currency currency = new Currency(currencyId, "LLL", "TESTN");
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(currency);
        QueryResponse response = new QueryResponse();
        response.setCurrencyList(currencyList);
        response.setStatus("Success");
        QueryRequest request = new QueryRequest();
        request.setId(currencyId);

        when(service.query(request)).thenReturn(response);

        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/query")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Adjust the expected status code as needed
                .andExpect(jsonPath("$.STATUS").value("Success"))
                .andReturn();

        // Assert
        verify(service, times(1)).query(request);
    }

    @Test
    void testGetCurrencyById_NotFound() throws Exception {
        // Arrange
        long nonExistsId = 2L;
        Currency currency = new Currency(nonExistsId, "LLL", "TESTN");
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(currency);
        QueryResponse response = new QueryResponse();
        response.setStatus("Fail, record not found.");
        QueryRequest request = new QueryRequest();
        request.setId(nonExistsId);

        when(service.query(request)).thenReturn(response);

        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Act
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/query")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Adjust the expected status code as needed
                .andExpect(jsonPath("$.STATUS").value("Fail, record not found."))
                .andReturn();
        
        
        // Assert
        verify(service, times(1)).query(request);
    }


}
