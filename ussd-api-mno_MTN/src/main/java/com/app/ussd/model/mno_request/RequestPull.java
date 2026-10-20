package com.app.ussd.model.mno_request;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@XmlRootElement(name="request")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPull { 
	@Getter @Setter
	private String language;
	@Getter @Setter
	private String msisdn;
	@Getter @Setter
	private String newRequest;
	@Getter @Setter
	private Parameters parameters;
	@Getter @Setter
	private String region;
	@Getter @Setter
	private Freeflow freeflow;
	@Getter @Setter
	private String sessionId;
	@Getter @Setter
	private String subscriberInput;
	@Getter @Setter
	private String transactionId;
	@Getter @Setter
	private String type;
	@Getter @Setter
	private String text;
}



