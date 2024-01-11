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
public class QueryRequest {

    @ApiModelProperty(value = "ID", required = false)
    @JsonProperty("ID")
    private Long id;

    @ApiModelProperty(value = "幣別代碼", required = false)
    @JsonProperty("CODE")
    private String code;

    @ApiModelProperty(value = "幣別名稱", required = false)
    @JsonProperty("NAME")
    private String name;
}
