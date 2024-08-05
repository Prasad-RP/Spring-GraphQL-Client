package com.spring.graphql.dto;

	import com.fasterxml.jackson.annotation.JsonInclude;
	import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT) // it will remove all null or 0 values
public class Product {

	private Integer productId;

	private String productName;

	private Float price;

	private Integer stock;

}
