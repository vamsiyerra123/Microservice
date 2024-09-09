package com.javatechie.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.javatechie.config.AppConfig;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

//    @Autowired
//    private JwtUtil jwtUtil;

	@Autowired
	@Lazy
	private AppConfig appConfig;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate template;

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			if (validator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing authorization header");
				}

				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				} else {
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid authorization header");
				}

				try {
					System.out.println("Token to validate: " + authHeader);
					ServiceInstance serviceInstance = loadBalancerClient.choose("IDENTITY-SERVICE");
					String uri = serviceInstance.getUri().toString();
					String contextRoot = serviceInstance.getMetadata().get("configPath");
//            		AddressResponse addressResponse =restTemplate.getForObject(uri+contextRoot+"/address/{employeeID}", AddressResponse.class, employeeID);
					String response = template.getForObject(uri+contextRoot+"/validate?token=" + authHeader,
							String.class);
					System.out.println("Response from employee-service: " + response);
				} catch (Exception e) {
					System.out.println("Token validation failed for token: " + authHeader);
					e.printStackTrace();
					throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
							"Unauthorized access to the application");
				}
			}

			return chain.filter(exchange);
		};
	}

	public static class Config {
		// Configuration class if needed for future extension
	}
}
