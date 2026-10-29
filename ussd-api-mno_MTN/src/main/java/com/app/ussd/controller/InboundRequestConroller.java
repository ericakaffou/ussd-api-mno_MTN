package com.app.ussd.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.ussd.service.MnoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("${app.root.url}" + "/mno-inbound")
public class InboundRequestConroller {
	
	private final MnoService manager;

	public InboundRequestConroller(MnoService manager) {	
		this.manager = manager;
	}
	
	@PostMapping("/")
	public  String Inbound(@RequestBody String xmlRequest) {
		
		return manager.ussdService(xmlRequest);
	}

}
