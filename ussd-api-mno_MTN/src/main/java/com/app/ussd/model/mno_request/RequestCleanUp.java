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
public class RequestCleanUp {
	@Getter @Setter
	private String cellId;
	@Getter @Setter
	private String dateFormat;
	@Getter @Setter
	private String destRef;
	@Getter @Setter
	private String gsmMapUserSpecVal;
	@Getter @Setter
	private String hlr;
	@Getter @Setter
	private String imsi;
	@Getter @Setter
	private String language;
	@Getter @Setter
	private String msc;
	@Getter @Setter
	private String msisdn;
	@Getter @Setter
	private String nodeIdentifier;
	@Getter @Setter
	private String orgRef;
	@Getter @Setter
	private Parameters parameters;
	@Getter @Setter
	private String region;
	@Getter @Setter
	private String sessionId;
	@Getter @Setter
	private String statusCode;
	@Getter @Setter
	private String transactionId;
	@Getter @Setter
	private String vlr;
	@Getter @Setter
	private String location;
	@Getter @Setter
	private String type;
	@Getter @Setter
	private String text;

}
