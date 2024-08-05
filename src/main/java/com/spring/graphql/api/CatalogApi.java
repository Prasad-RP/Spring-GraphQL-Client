package com.spring.graphql.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.graphql.dto.Product;
import com.spring.graphql.service.CatalogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/catalog")
public class CatalogApi {

	private final CatalogService catalogService;

	@GetMapping("/products")
	public ResponseEntity<Map<Object, Object>> getAllProducts() {
		Map<Object, Object> map = new HashMap<>();
		map.put("SUCCESS", true);
		map.put("DATA", catalogService.viewProducts());
		return ResponseEntity.ok(map);
	}

	@GetMapping("/products/category/{category}")
	public ResponseEntity<Map<Object, Object>> getAllProductsByCategory(@PathVariable String category) {
		Map<Object, Object> map = new HashMap<>();
		map.put("SUCCESS", true);
		map.put("DATA", catalogService.viewProductsByCategory(category));
		return ResponseEntity.ok(map);
	}

	@PostMapping("/shipment")
	public ResponseEntity<Map<Object, Object>> addNewShipment(@RequestBody Product product) {
		Map<Object, Object> map = new HashMap<>();
		map.put("SUCCESS", true);
		map.put("DATA", catalogService.receiveNewShipment(product));
		return ResponseEntity.ok(map);
	}
}
