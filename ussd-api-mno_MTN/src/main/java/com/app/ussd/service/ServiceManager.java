package com.app.ussd.service;

/* Ce service est le service d'entrée de app2. 
C'est ici que le service concerné est indentifié  : ?????? pb ??? : bah non on ne devrait pas identifier le service dans app2
car app2 est concu pour un service unique! donc l'identification devrait se faire ailleur !!!
idée : ce service doit etre deplacer dans app3 
Il utilisera l'url du service pour transfer app2Data vers app2 */

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import com.app.ussd.repository.UssdSessionRepository;
import com.app.ussd.repository.UssdTransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceManager {	

	private final UssdSessionRepository ussdRepo;	
	private final FormattageService formattageService;
	private final UssdTransactionRepository transactionRepo;



	public ServiceManager( UssdSessionRepository ussdRepo , FormattageService formattageService,
		                   UssdTransactionRepository transactionRepo	) {		
		this.ussdRepo = ussdRepo;
		this.formattageService = formattageService;
		this.transactionRepo = transactionRepo;
	}

	public String ussdService(  String xmlRequest) {		
		
		//log.info("App2 - InboundRequestConroller");
		
		 // premiere chose recuperer le type de request : pull or cleanup
		Document xmlDocument =formattageService.parseText(xmlRequest);		  
	    String type = xmlDocument.getElementsByTagName("request").item(0).getAttributes().getNamedItem("type").getNodeValue(); 
	          
		
		 if(type.matches("pull")) {
            /* instanciation sans injection de PullRequestService pour ne pas injecter PullRequestService meme dans le cas
			d'un Clean */
			PullRequestService requestService = new PullRequestService(ussdRepo, transactionRepo);
			return requestService.pullRequest(type, xmlRequest);
			
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
