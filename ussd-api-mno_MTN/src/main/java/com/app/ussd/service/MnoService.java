package com.app.ussd.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MnoService {	

	private final PullRequestService pullRequestService;
	private final FormattageService formattageService;
	
	public MnoService(PullRequestService pullRequestService, FormattageService formattageService) {
		this.pullRequestService = pullRequestService;
		this.formattageService = formattageService;
	}

	public String ussdService(  String xmlRequest) {		
		
		 // premiere chose recuperer le type de request : pull or cleanup
		Document xmlDocument =formattageService.parseText(xmlRequest);		  
	    String type = xmlDocument.getElementsByTagName("request").item(0).getAttributes().getNamedItem("type").getNodeValue();  
		
		 if(type.matches("pull")) {

			return pullRequestService.pullRequest(type, xmlRequest);			
		 }
		else {
			 /* instanciation sans injection de CleanRequestService pour ne pas injecter CleanRequestService meme dans le cas
			d'un Pull */
			CleanRequestService requestService = new CleanRequestService();
			return requestService.cleanRequest(type, xmlRequest);
		}	
		
	}

	 /*
	 ----------------------------------------------------------------------------------------------------------------------------------------------------
	   */	
}                          // endf class
