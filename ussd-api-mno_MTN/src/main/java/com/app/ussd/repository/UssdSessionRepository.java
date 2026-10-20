package com.app.ussd.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.ussd.model.app.UssdSession;

public interface UssdSessionRepository extends JpaRepository<UssdSession, Long> {
 
	public Optional<UssdSession> findByUssdSessionId(String id); 
	
	
}
