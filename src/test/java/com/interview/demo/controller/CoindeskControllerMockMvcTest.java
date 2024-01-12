package com.interview.demo.controller;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.interview.demo.service.impl.CoindeskServiceImpl;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

@WebMvcTest(CoindeskController.class)
public class CoindeskControllerMockMvcTest {

	@Mock
	CoindeskServiceImpl service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetCoindesk_Success() throws Exception {
		// Arrange
		when(service.queryRow()).thenReturn(null);

		// Act & Assert
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/queryRow")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Data for ID "))
				.andReturn();

		// Verify
		verify(service, times(1)).queryRow();
	}

	@Test
	void testGetCoindeskAndCurrency_Success() throws Exception {
		// Arrange
		when(service.query()).thenReturn(null);

		// Act & Assert
		mockMvc.perform(MockMvcRequestBuilders.post("/queryRow")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.message").value("Data for ID "));

		// Verify
		verify(service, times(1)).query();
	}
}
