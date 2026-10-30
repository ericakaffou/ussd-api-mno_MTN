package com.app.ussd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.ussd.model.app.App2Data;
import com.app.ussd.model.app.UssdService;
import com.app.ussd.model.app.UssdSession;
import com.app.ussd.model.mno_request.RequestPull;

@Service
public class NewRequestService {

     private final UssdSessionService sessionService;
	 private final TransactionService transactionService;
     private final RoutingService routing; 
     @Value("${COUNTRYCODE_INDEX}")  
     private int CC_index;   

    public NewRequestService(UssdSessionService sessionService, TransactionService transactionService,
            RoutingService routing) {
        this.sessionService = sessionService;
        this.transactionService = transactionService;
        this.routing = routing;
    }

    public String newRequest( RequestPull request){

                String response = null ;	
				//Check if session Existe on DB	    
			 Optional<UssdSession> optionalSession  = sessionService.findByUssdSessionId(request.getSessionId());	

                        // Check if sessionId exist
                            if(optionalSession.isPresent()) {
                                response = "Session already exist";
                                return response;
                            }
							String[] parts =null;
                            String SubscriberInput = request.getSubscriberInput().replace("#", "");
                        
                        try {  /* Faire attention avec le parametre de delimitage ceci pourrait créer un soucis pour une syntaxe comme #100* */
                            String delimiter = "\\*";
                            parts = SubscriberInput.split(delimiter);                            
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }
                     
                    // ici on recupère le service et on pourra verifier si forwarded ou non
					// si forwaded on devra recuperer les routeMno pour on les avait deja dans ServiceRouting
		 			Optional<UssdService> optionalscService = routing.GetService(parts, request.getMsisdn().substring(0, CC_index)); 
		 			if(optionalscService.isEmpty()) {
		 				response = "Routing Error";
		 				return (response);		 				
		 			}
                                        
                    // enregistrement de la session et Transaction
                    UssdSession session =  sessionService.save(request, optionalscService.get())  ;                  
					transactionService.save(request,session ); 

                    // ici Si le service est forwarded envoyer app2Data à l'url (serviceForwardedUrl)
                    // Si le service n'est forwarded envoyer app2Data à l'url (app2Url)

                    // construction de app2Data
                    App2Data app2request = new App2Data();
		 			app2request.setSession(session);
		 			app2request.setParts(parts);
		 			app2request.setNewSession(true);

                            // ici on a recuperé le SubscriberInput dans le tableau parts
                            // on doit maintenant identifier le service et recuperer l'url de son app2 associé
                            // faire un test et retourner les part et après un autre pour le serviceId

                           String msg = request.getMsisdn().substring(0, CC_index);
                           return msg;
    }

}
