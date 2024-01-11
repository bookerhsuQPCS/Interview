package com.interview.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.demo.entity.Currency;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class QueryResponse extends BaseResponse {
    
	@ApiModelProperty(value = "比率清單")
    @JsonProperty("currencyList")
    private List<Currency> currencyList;
}
