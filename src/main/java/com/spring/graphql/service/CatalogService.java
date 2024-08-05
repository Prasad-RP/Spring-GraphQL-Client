package com.spring.graphql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.graphql.client.InventoryClient;
import com.spring.graphql.dto.Product;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogService {

	private final InventoryClient inventoryClient;

	public List<Product> viewProducts() {
		return inventoryClient.callViewProducts();
	}

	public List<Product> viewProductsByCategory(String category) {
		return inventoryClient.callViewProductsByCategory(category);
	}

	public Product receiveNewShipment(Product product) {
		return inventoryClient.callReceiveNewShipment(product);
	}

}
