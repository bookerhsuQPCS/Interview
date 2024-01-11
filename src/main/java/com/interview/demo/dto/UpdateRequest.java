package com.interview.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class UpdateRequest {

    @ApiModelProperty(value = "ID", required = true)
    @JsonProperty("ID")
    @NotNull(message = "ID不能空白。")
    private Long id;

    @ApiModelProperty(value = "幣別代碼", required = true)
    @Size(max=3, min=3, message = "幣別代碼:[${validatedValue}] 必須為英文大寫三碼。")
    @JsonProperty("CODE")
    private String code;

    @ApiModelProperty(value = "幣別名稱", required = true)
    @NotBlank(message = "幣別名稱不能空白。")
    @JsonProperty("NAME")
    private String name;
}
