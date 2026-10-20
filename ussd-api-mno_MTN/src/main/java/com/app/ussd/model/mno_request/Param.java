package com.app.ussd.model.mno_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {
	
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String text;

}
