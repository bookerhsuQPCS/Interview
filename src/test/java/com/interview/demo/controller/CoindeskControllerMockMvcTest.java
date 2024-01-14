package com.interview.demo.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.demo.dto.QueryCoindeskResponse;
import com.interview.demo.service.impl.CoindeskServiceImpl;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class CoindeskControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private CoindeskServiceImpl service;

	@Mock
	private QueryCoindeskResponse queryCoindeskResponseMock;

	@InjectMocks
	private CoindeskController controller;

	@Test
	public void testGetCoindeskRaw_OK() throws Exception {
		// Prepare the request content as JSON
		String jsonOutput = "{\"time\":{\"updated\":\"Jan 11, 2024 00:07:41 UTC\",\"updatedISO\":\"2024-01-11T00:07:41+00:00\",\"updateduk\":\"Jan 11, 2024 at 00:07 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"46,505.936\",\"description\":\"United States Dollar\",\"rate_float\":46505.9356},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"36,502.695\",\"description\":\"British Pound Sterling\",\"rate_float\":36502.6949},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"42,381.696\",\"description\":\"Euro\",\"rate_float\":42381.6963}}}";
		ObjectMapper objectMapper = new ObjectMapper();
	    QueryCoindeskResponse response = objectMapper.readValue(jsonOutput, QueryCoindeskResponse.class);

		// Mocking behavior using when and thenReturn for the YourService class
		when(service.getCoindeskRaw()).thenReturn(response);

		
		// Set up the controller with the mocks
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Perform the POST request simulation using MockMvc
		MvcResult result = mockMvc
				.perform(post("/getCoindeskRaw").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.time.updated", Matchers.is("Jan 11, 2024 00:07:41 UTC")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.disclaimer", Matchers.is("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.chartName", Matchers.is("Bitcoin")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.code", Matchers.is("USD")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.symbol", Matchers.is("&#36;")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.rate", Matchers.is("46,505.936")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.description", Matchers.is("United States Dollar")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.rate_float", Matchers.is(46505.9356)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.GBP.code", Matchers.is("GBP")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.GBP.symbol", Matchers.is("&pound;")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.GBP.rate", Matchers.is("36,502.695")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.GBP.description", Matchers.is("British Pound Sterling")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.GBP.rate_float", Matchers.is(36502.6949)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.code", Matchers.is("EUR")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.symbol", Matchers.is("&euro;")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.rate", Matchers.is("42,381.696")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.description", Matchers.is("Euro")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.rate_float", Matchers.is(42381.6963)))
				.andReturn();

		// Extract and assert the response content
		String jsonResponse = result.getResponse().getContentAsString();
		System.out.println("jsonResponse Content: " + jsonResponse);
		// Further assertions if needed
	}

	@Test
	public void testGetCoindeskAndTnTime_OK() throws Exception {
		// Prepare the request content as JSON
		String jsonOutput = "{\"time\": { \"updated\": \"2024/01/14 11:11:21\", \"updatedISO\": \"2024-01-14T11:11:21.121+08:00[Asia/Taipei]\", \"updateduk\": \"一月 14, 2024 at 11:11 TST\"  },  \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",  \"chartName\": \"Bitcoin\",  \"bpi\": { \"USD\": {\"code\": \"USD\",\"symbol\": \"&#36;\",\"rate\": \"42,880.634\",\"description\": \"United States Dollar\",\"rate_float\": 42880.6338,\"cname\": \"美元\" }, \"GBP\": {\"code\": \"GBP\",\"symbol\": \"&pound;\",\"rate\": \"33,642.43\",\"description\": \"British Pound Sterling\",\"rate_float\": 33642.4301,\"cname\": \"英鎊\" }, \"EUR\": {\"code\": \"EUR\",\"symbol\": \"&euro;\",\"rate\": \"39,110.397\",\"description\": \"Euro\",\"rate_float\": 39110.397,\"cname\": \"歐元\" }  }}";
		ObjectMapper objectMapper = new ObjectMapper();
	    QueryCoindeskResponse response = objectMapper.readValue(jsonOutput, QueryCoindeskResponse.class);

		// Mocking behavior using when and thenReturn for the YourService class
		when(service.getCoindeskAndTnTime()).thenReturn(response);
		
		// Set up the controller with the mocks
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		// Perform the POST request simulation using MockMvc
		MvcResult result = mockMvc
				.perform(post("/getCoindeskAndTnTime").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.time.updated", Matchers.is("2024/01/14 11:11:21")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.disclaimer", Matchers.is("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.chartName", Matchers.is("Bitcoin")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.code", Matchers.is("USD")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.symbol", Matchers.is("&#36;")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.rate", Matchers.is("42,880.634")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.description", Matchers.is("United States Dollar")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.rate_float", Matchers.is(42880.6338)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.USD.cname", Matchers.is("美元")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.code", Matchers.is("EUR")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.symbol", Matchers.is("&euro;")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.rate", Matchers.is("39,110.397")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.description", Matchers.is("Euro")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.rate_float", Matchers.is(39110.397)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi.EUR.cname", Matchers.is("歐元")))
				.andReturn();

		// Extract and assert the response content
		String jsonResponse = result.getResponse().getContentAsString();
		System.out.println("jsonResponse Content: " + jsonResponse);
		// Further assertions if needed
	}
}
