package com.app.ussd.service;

import org.springframework.stereotype.Service;

import com.app.ussd.model.app.UssdSession;
import com.app.ussd.model.app.UssdSessionTransaction;
import com.app.ussd.model.mno_request.RequestPull;
import com.app.ussd.repository.UssdTransactionRepository;

@Service
public class TransactionService {

     private final UssdTransactionRepository transactionRepo;

     public TransactionService(UssdTransactionRepository transactionRepo) {
        this.transactionRepo = transactionRepo;
     }

      public UssdSessionTransaction save(RequestPull request , UssdSession session ){


         // Save transaction
		 			 UssdSessionTransaction trans = new UssdSessionTransaction();	
		 			 trans.setUssdSession(session);
		 			 trans.setTransactionId(request.getTransactionId());
		 			 trans.setSubscriberInput(request.getSubscriberInput());		 			 
		 			 return transactionRepo.save(trans);		 

      }

}
