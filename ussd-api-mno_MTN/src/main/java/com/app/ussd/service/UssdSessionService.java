package com.app.ussd.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ussd.model.app.UssdService;
import com.app.ussd.model.app.UssdSession;
import com.app.ussd.model.mno_request.RequestPull;
import com.app.ussd.repository.UssdSessionRepository;

@Service
public class UssdSessionService {

    private final UssdSessionRepository ussdRepo;
    

    public UssdSessionService(UssdSessionRepository ussdRepo) {
        this.ussdRepo = ussdRepo;
    }


    public UssdSession save(RequestPull request , UssdService service ){

         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date date = new Date();

 // Save session
					 UssdSession session = new UssdSession();
					 session.setUssdSessionId(request.getSessionId());
					 session.setUssdString(request.getSubscriberInput());
					 session.setUssdSessionStartTime(dateFormat.format(date));
					 session.setService(service);
					 session.setUssdSessionMsisdn(request.getMsisdn());
					 return ussdRepo.save(session);

        
    }


    public Optional <UssdSession> findByUssdSessionId(String id){

        return ussdRepo.findByUssdSessionId(id);
    } 

}
