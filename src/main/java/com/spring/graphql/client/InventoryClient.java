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
		String query = """
				query GetAllProductsByCategory {
				    getAllProductsByCategory(category: "%s") {
				        price
				        stock
				        categoryMaster {
				            categoryName
				            categoryId
				        }
				        productName
				        productId
				    }
				}
				""".formatted(category);
		return graphQlClient.document(query).retrieve("getAllProductsByCategory").toEntityList(Product.class).block();
	}

	public Product callReceiveNewShipment(Product product) {
		String query = """
				mutation ReceiveNewShipment {
				    receiveNewShipment(productId: "%s", stock: %d) {
				        productName
				        price
				        stock
				    }
				}
				""".formatted(product.getProductId(), product.getStock());

		return graphQlClient.document(query).retrieve("receiveNewShipment").toEntity(Product.class).block();
	}

}
