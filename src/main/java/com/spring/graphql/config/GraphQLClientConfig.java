package com.spring.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class GraphQLClientConfig {

	@Bean
	public HttpGraphQlClient graphQlClient() {
		log.info("configuring graphQL client....");
		WebClient webClient = WebClient.builder().baseUrl("http://localhost:9191/graphql").build();
		return HttpGraphQlClient.builder(webClient).build();
	}
}
