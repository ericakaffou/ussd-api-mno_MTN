package com.app.ussd.service;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.app.ussd.model.app.UssdSession;
import com.app.ussd.model.app.UssdSessionTransaction;
import com.app.ussd.model.mno_request.RequestPull;
import com.app.ussd.repository.UssdSessionRepository;
import com.app.ussd.repository.UssdTransactionRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class PullRequestService {
    // non defint en final car est injecté depui ServiceManager
    private  UssdSessionRepository ussdRepo;	

    // non defint en final car est injecté depui ServiceManager
    private  UssdTransactionRepository transactionRepo;
     

    public PullRequestService(UssdSessionRepository ussdRepo, UssdTransactionRepository transactionRepo) {
        this.ussdRepo = ussdRepo;
        this.transactionRepo = transactionRepo;
    } 
    
    public String pullRequest(String type, String xmlRequest){

		 RequestPull request = null ;
		 String response = null ;	
		 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();
	    	 
	    	 try
			 {
			     JAXBContext jaxbContext = JAXBContext.newInstance(RequestPull.class);	  
			     Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		  
			      request = (RequestPull) jaxbUnmarshaller.unmarshal(new StringReader(xmlRequest));
			     		       
			 }
			 catch (JAXBException e) 
			 {
			     e.printStackTrace();
				 }			 
			 // set RequestPull Type in Entity
			 request.setType(type); 			
			//Check if session Existe on DB	    
			 Optional<UssdSession> optionalSession  = ussdRepo.findByUssdSessionId(request.getSessionId());
			 
			  // ensuite check le param newRequest : new request = 1 / old request = 0 

			 // New Session
			 /*
			 ici dans la nouvelle session on enregistre l'ussdSession , on recupère le tableau de part puis
			 on construit l'objet app2Data puis on l'envoi à app2 (ussd-api-service)
			 on recupere le menu generé , on le format puis on le renvoi
			 */

			 //ici mettre tout dans cette meme classe	

			  if(request.getNewRequest().matches("1")) {

                        // Check if sessionId exist
                            if(optionalSession.isPresent()) {
                                response = "Session already exist";
                                return response;
                            }
                            // Save transaction
                            UssdSession session = new UssdSession();
                            session.setUssdSessionId(request.getSessionId());
                            session.setUssdString(request.getSubscriberInput());
                            session.setUssdSessionStartTime(dateFormat.format(date));
                            session.setUssdSessionMsisdn(request.getMsisdn());
                            ussdRepo.save(session);
                            
                            // Save transaction
                            UssdSessionTransaction trans = new UssdSessionTransaction();	
                            trans.setUssdSession(session);		 			 
                            trans.setTransactionId(request.getTransactionId());
                            trans.setSubscriberInput(request.getSubscriberInput());		 			 
                            transactionRepo.save(trans);

                            String[] parts =null;
                            //String SubscriberInput =request.getSubscriberInput().substring(0, request.getSubscriberInput().length()-1);
                            String SubscriberInput = request.getSubscriberInput().replace("#", "");
                        
                        try { 
                            /* Faire attention avec le parametre de delimitage
                            ceci pourrait créer un soucis pour une syntaxe comme #100* */
                            String delimiter = "\\*";
                            parts = SubscriberInput.split(delimiter);
                            
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }

                            // ici on a recuperé le SubscriberInput dans le tableau parts
                            // on doit maintenant identifier le service et recuperer l'url de son app2 associé

                            // faire un test et retourner les part et après un autre pour le serviceId

                           log.info("1"+parts[0]+"-2-"+parts[1]+"-3-"+parts[2]+"-4-");
                           String msg = "1"+parts[0]+"-2-"+parts[1]+"-3-"+parts[2]+"-4-";
                           return msg;
                            

			     }

			  





		return null;
	}

}
