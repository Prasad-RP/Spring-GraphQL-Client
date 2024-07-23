package com.spring.graphql.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import com.spring.graphql.dto.Product;

@Component
public class InventoryClient {

	@Autowired
	private HttpGraphQlClient graphQlClient;

	public List<Product> viewProducts() {
		// This is GraphQL query
		String query = "query GetAllProducts {getAllProducts { productName price }";
		// block
		return graphQlClient.document(query).retrieve("getAllProducts").toEntityList(Product.class).block();
	}

	public List<Product> viewProductsByCategory(String category) {
		String query = String.format("query GetAllProductsByCategory {\r\n"
				+ "    getAllProductsByCategory(category: \"%s\") {\r\n" + "        productId\r\n"
				+ "        productName\r\n" + "        price\r\n" + "        stock\r\n" + "    }\r\n" + "}\r\n" + "",
				category);
		return graphQlClient.document(query).retrieve("getAllProductsByCategory").toEntityList(Product.class).block();
	}

}
