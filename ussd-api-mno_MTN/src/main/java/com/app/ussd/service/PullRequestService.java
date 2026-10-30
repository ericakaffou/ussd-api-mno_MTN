package com.app.ussd.service;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;
import com.app.ussd.model.mno_request.RequestPull;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service 
public class PullRequestService {
   
    private final NewRequestService newRequestService;
	private final OldRequestService oldRequestService;   


	public PullRequestService(NewRequestService newRequestService, OldRequestService oldRequestService) {
		this.newRequestService = newRequestService;
		this.oldRequestService = oldRequestService;
	}

	public String pullRequest(String type, String xmlRequest){

		 RequestPull request = null ;
		 String response = null ;		      
	    	 
	    	 try { JAXBContext jaxbContext = JAXBContext.newInstance(RequestPull.class);	  
			     Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();		  
			      request = (RequestPull) jaxbUnmarshaller.unmarshal(new StringReader(xmlRequest));			     		       
			 }
			 catch (JAXBException e) 
			 {  e.printStackTrace();
				 }			 
			 // set RequestPull Type in Entity
			 request.setType(type); 			
					 
			  // ensuite check le param newRequest : new request = 1 / old request = 0 
			 // New Session
			 /*
			 ici dans la nouvelle session on enregistre l'ussdSession , on recupère le tableau de part puis
			 on construit l'objet app2Data puis on l'envoi à app2 (ussd-api-service)
			 on recupere le menu generé , on le format puis on le renvoi
			 */
			 //New Session	
			  if(request.getNewRequest().matches("1")) {
                         
				response = newRequestService.newRequest(request);

			     }
				// Old Session
	    	  if(request.getNewRequest().matches("0")) {

				 
			    }

			  





		return response;
	}

}
