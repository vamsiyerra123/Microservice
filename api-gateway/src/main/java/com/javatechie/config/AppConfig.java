package com.javatechie.config;
//

import org.springframework.cloud.client.loadbalancer.LoadBalanced;

//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//
//@FeignClient(name = "IDENTITY-SERVICE")

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
//	
//	@GetMapping("/validate")
//    public String validateToken(@RequestParam("token") String token);
//
	@LoadBalanced
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

	
}
