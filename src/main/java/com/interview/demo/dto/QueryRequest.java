package com.interview.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class QueryRequest {

    @ApiModelProperty(value = "幣別代碼", required = true)
    @NotBlank(message = "幣別名稱不能代碼。")
    @Size(max=3, min=3, message = "幣別代碼:[${validatedValue}] 必須為英文大寫三碼。")
    @JsonProperty("CODE")
    private String code;
}
