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

	// READ Operations
	public List<Product> callViewProducts() {
		// This is GraphQL query
		String query = "query GetAllProducts {getAllProducts { productName price }}";
		// block the reactive call.
		return graphQlClient.document(query).retrieve("getAllProducts").toEntityList(Product.class).block();
	}

	public List<Product> callViewProductsByCategory(String category) {
		String query = String.format(
				"query GetAllProductsByCategory {\r\n"
				+ "    getAllProductsByCategory(category: \"%s\") {\r\n"
				+ "        price\r\n"
				+ "        stock\r\n"
				+ "        categoryMaster {\r\n"
				+ "            categoryName\r\n"
				+ "            categoryId\r\n"
				+ "        }\r\n"
				+ "        productName\r\n"
				+ "        productId\r\n"
				+ "    }\r\n"
				+ "}\r\n"
				+ "",
				category);
		return graphQlClient.document(query).retrieve("getAllProductsByCategory").toEntityList(Product.class).block();
	}

	// WRITE Operations
	public Product callReceiveNewShipment(Product product) {
		String query = String.format("mutation ReceiveNewShipment {\r\n"
				+ "    receiveNewShipment(productId: \"%s\", stock: %d) {\r\n" + "        productName\r\n"
				+ "        price\r\n" + "        stock\r\n" + "    }\r\n" + "}\r\n" + "", product.getProductId(),
				product.getStock());

		return graphQlClient.document(query).retrieve("receiveNewShipment").toEntity(Product.class).block();
	}
}
