package com.app.ussd.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.app.ussd.model.app.App2Data;
import com.app.ussd.model.app.UssdSession;
import com.app.ussd.model.mno_request.RequestPull;

@Service 
public class OldRequestService {

     private final UssdSessionService sessionService;
	 private final TransactionService transactionService;
     private final FormattageService formatage;
 

     public OldRequestService(UssdSessionService sessionService, TransactionService transactionService, FormattageService formatage) {
        this.sessionService = sessionService;
        this.transactionService = transactionService;
        this.formatage = formatage;
       
     } 
     
     public String newRequest( RequestPull request){

         String response = null ;	
				//Check if session Existe on DB	    
			 Optional<UssdSession> optionalSession  = sessionService.findByUssdSessionId(request.getSessionId());	
         // Check if sessionId exist
	    		     if(!optionalSession.isPresent()) {
	    		    	 response = "Session Not Found";
	    		    	 return response;
	    		     }    		     
	 			 
	    	UssdSession session = optionalSession.get();
			transactionService.save(request,session );

            App2Data app2request = new App2Data();
	 		app2request.setSession(session);
	 		app2request.setInput(request.getSubscriberInput());
	 		app2request.setNewSession(false);

            /* verifier si le service est forwarded ou non 
               recuperer app2url du service */

              // response = app2Client.toApp2(App2request);
              response = formatage.ResPullToXML(formatage.setResponse(request, response, true));
        return response ;
     }

     

}
