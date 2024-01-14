package com.interview.demo.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "currency")
@AllArgsConstructor
@NoArgsConstructor
public class Currency implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	@JsonProperty("ID")
	@Id
	@Column(name = "id", length = 4)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@GenericGenerator(name = "id", strategy = "com.interview.demo.entity.ManualInsertGenerator")
	private Long id;

    @ApiModelProperty(value = "幣別代碼", required = false)
    @JsonProperty("CODE")
	@Column(name = "CODE")
	private String code;
    
    @ApiModelProperty(value = "幣別名稱")
    @JsonProperty("NAME")
	@Column(name = "name")
	private String name;
}
