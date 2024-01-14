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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.dto.AddRequest;
import com.interview.demo.dto.DelRequest;
import com.interview.demo.dto.QueryRequest;
import com.interview.demo.dto.QueryResponse;
import com.interview.demo.dto.UpdateRequest;
import com.interview.demo.entity.Currency;
import com.interview.demo.service.impl.CurrencyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CurrencyControllerMockMvcTest {

	@InjectMocks
	private CurrencyController controller;

	@Mock
	private CurrencyServiceImpl service;

	@Autowired
	private MockMvc mockMvc;

	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@Test
	void testGetCurrency_Found() throws Exception {

		long currencyId = 1L;
		Currency currency = new Currency(currencyId, "LLL", "TESTN");
		List<Currency> currencyList = new ArrayList<>();
		currencyList.add(currency);
		QueryResponse response = new QueryResponse();
		response.setCurrencyList(currencyList);
		response.setStatus("Success");

		when(service.findAll()).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/findAll")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Adjust the expected status code as needed
				.andExpect(jsonPath("$.STATUS").value("Success")).andReturn();

		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);

		// Assert
		verify(service, times(1)).findAll();
	}

	@Test
	void testGetCurrencyByCode_OK() throws Exception {

		long currencyId = 111L;
		Currency currency = new Currency(currencyId, "TBY", "測試");
		List<Currency> currencyList = new ArrayList<>();
		currencyList.add(currency);
		QueryResponse response = new QueryResponse();
		response.setCurrencyList(currencyList);
		response.setStatus("Success");
		QueryRequest request = new QueryRequest();
		request.setCode("TBY");

		when(service.queryByCode(request)).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/findByCode").content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Adjust the expected status code as needed
				.andExpect(jsonPath("$.STATUS").value("Success"))
                .andExpect(jsonPath("$.currencyList").isArray())
                .andExpect(jsonPath("$.currencyList.length()").value(1)) // Assuming the list has 1 elements
                .andExpect(jsonPath("$.currencyList[0].CODE").value("TBY"))
                .andExpect(jsonPath("$.currencyList[0].NAME").value("測試"))
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);

		// Assert
		verify(service, times(1)).queryByCode(request);
	}
	
	@Test
	void testAddCurrency_Success() throws Exception {
		// Arrange
		AddRequest requestData = new AddRequest();
		requestData.setCode("NTD");
		requestData.setName("新台幣");
		QueryResponse response = new QueryResponse();
		response.setStatus("Success");

		when(service.add(requestData)).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Act
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/add").content(objectMapper.writeValueAsString(requestData))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.STATUS").value("Success")).andReturn();

		// Assert
		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);
		verify(service, times(1)).add(requestData);
	}

	@Test
	void testUpdateCurrency_Success() throws Exception {
		// Arrange
		Long currencyId = 11L;
		UpdateRequest requestData = new UpdateRequest();
		requestData.setId(currencyId);
		requestData.setCode("NTD");
		requestData.setName("新台幣");
		QueryResponse response = new QueryResponse();
		response.setStatus("Success");
		when(service.update(requestData)).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Act
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/update").content(objectMapper.writeValueAsString(requestData))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.STATUS").value("Success")).andReturn();

		// Assert
		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);
		verify(service, times(1)).update(requestData);
	}

	@Test
	void testUpdateCurrency_Failure() throws Exception {
		// Arrange
		Long failCurrencyId = 222L;
		UpdateRequest requestData = new UpdateRequest();
		requestData.setId(failCurrencyId);
		requestData.setCode("NTT");
		requestData.setName("新台幣F");
		QueryResponse response = new QueryResponse();
		response.setStatus("Fail, Record not found.");
		when(service.update(requestData)).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Act
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/update").content(objectMapper.writeValueAsString(requestData))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.STATUS").value("Fail, Record not found."))
				.andReturn();

		// Assert
		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);
		verify(service, times(1)).update(requestData);
	}

	@Test
	void testDeleteCurrency_Success() throws Exception {
		// Arrange
		Long currencyId = 1111L;
		DelRequest request = new DelRequest();
		request.setId(currencyId);
		QueryResponse response = new QueryResponse();
		response.setStatus("Success");
		when(service.del(request)).thenReturn(response);

		// Setup MockMvc
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Act
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/del")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		// Assert
		String responseContent = result.getResponse().getContentAsString();
		System.out.println("Response Content: " + responseContent);
		verify(service, times(1)).del(request);
	}

}