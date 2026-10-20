package com.app.ussd.model.app;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCode extends AbstractEntity{	
		
	private long serviceCode;	
	
	private boolean dedicated ;	// ture : dedicated ; false : shared
	
	
}
