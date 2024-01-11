package com.interview.demo.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryCoindeskResponse {

	@JsonProperty("time")
	private TnTime tNtime;
	
	@JsonProperty("disclaimer")
	private String disclaimer;
	
	@JsonProperty("chartName")
	private String chartName;
	
	@JsonProperty("bpi")
	private Map<String,Bpi> bpi;
	
	@Data
	@NoArgsConstructor
	public static class TnTime {
	    @JsonProperty("updated")
	    private String updated;

	    @JsonProperty("updatedISO")
	    private String updatedISO;

	    @JsonProperty("updateduk")
	    private String updateduk;
	}
	
	@Data
	@NoArgsConstructor
	public static class Bpi {
		
		@JsonProperty("code")
		private String code;
		
		@JsonProperty("symbol")
		private String symbol;
		
		@JsonProperty("rate")
		private String rate;
		
		@JsonProperty("description")
		private String description;
		
		@JsonProperty("rate_float")
		private BigDecimal rateFloat;
		
		@JsonProperty("cname")
		@JsonInclude(JsonInclude.Include.NON_NULL)
		private String cname;
	}

}
