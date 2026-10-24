package com.app.ussd.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.app.ussd.model.app.App2Data;

@Service
public class App2Client {
	
	 private final WebClient webClient;
	 
	 public App2Client(WebClient.Builder builder) {
	        this.webClient = builder
	                .baseUrl("${app2.service.url}")
	                .build();
	    }
	  
	  public String send(App2Data app2request) {
		  
		  
		  return webClient.post()
				  .uri("/app2inbound")
				  .bodyValue(app2request)
				  .retrieve()
				  .bodyToMono(String.class)
				  .block();
	  }

}
