package com.interview.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

	@ApiModelProperty(value = "狀態")
    @JsonProperty("STATUS")
    public String status;

	@ApiModelProperty(value = "系統訊息")
    @JsonProperty("MSG")
    public String message;
}
