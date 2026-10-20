package com.app.ussd.model.mno_response;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name="response")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCleanUp {
	@Getter @Setter
	private String msisdn;
	@Getter @Setter
	public String statusCode;

}
