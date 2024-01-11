package com.interview.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddRequest {

    @ApiModelProperty(value = "幣別代碼", required = true)
    @NotBlank(message = "幣別名稱不能代碼。")
    @Size(max=3, min=3, message = "幣別代碼:[${validatedValue}] 必須為英文大寫三碼。")
    @JsonProperty("CODE")
    private String code;

    @ApiModelProperty(value = "幣別名稱", required = true)
    @NotBlank(message = "幣別名稱不能空白。")
    @JsonProperty("NAME")
    private String name;
}
