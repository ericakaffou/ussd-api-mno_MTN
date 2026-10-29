package com.app.ussd.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.app.ussd.model.app.Covrage;
import com.app.ussd.model.app.ServiceCode;
import com.app.ussd.model.app.UssdService;
import com.app.ussd.repository.CovrageRepository;
import com.app.ussd.repository.ServiceCodeRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ServiceRouting {
	
	private final ServiceCodeRepository scRepo;	
	private final CovrageRepository  covrageRepo ;
	public ServiceRouting(ServiceCodeRepository scRepo, CovrageRepository covrageRepo) {
		super();
		this.scRepo = scRepo;
		this.covrageRepo = covrageRepo;		
	}
	public Optional <UssdService> GetService( String[] part ,  String countryCode) {
		
		//Recuperation du serviceCode
		Optional <ServiceCode> optionalSc = scRepo.findServiceCodeForCountry(Long.valueOf(part[1]) ,countryCode );
		if(optionalSc.isEmpty()) {
			log.info("Service code Not Found");	
			return Optional.empty();
		}
		ServiceCode sc = optionalSc.get();
		Optional<Covrage> optionalCovrage = null;
	    //Recuperation du Covrage  ??? ajouter le pays dans la recherche ??? non car la recherche du covrage se fait avec les ServiceCodeId ,
		// pour 2 pays ayant le sc #122# à l'ajout on aura 2 lignes de scId (1 par pays) , donc le covrage retournera le bon
		if(sc.isDedicated()) {		    
			 optionalCovrage = covrageRepo.findByServiceCodeId(sc.getId());
		}else {
			 optionalCovrage = covrageRepo.findByServiceCodeIdAndChannel(sc.getId(), part[2]);
		}
		
		if(optionalCovrage.isEmpty()) {
			log.info("Covrage Not Set ");	
			return Optional.empty();			
		}

		// ici recuperer le service , verifie si dédicated 
		//UssdService scService = otionalRouteMno.get().getService();
		
		return null ;
	}

}
