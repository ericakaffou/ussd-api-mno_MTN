package com.app.ussd.model.mno_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Freeflow {
	
	@Getter @Setter
	private String mode;
	@Getter @Setter
	private String freeflowState;
	@Getter @Setter
	private String freeflowCharging;
	@Getter @Setter
	private String freeflowChargingAmount;
	
	

}
