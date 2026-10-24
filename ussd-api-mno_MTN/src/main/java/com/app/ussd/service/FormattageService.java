package com.app.ussd.service;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.app.ussd.model.mno_request.RequestCleanUp;
import com.app.ussd.model.mno_request.RequestPull;
import com.app.ussd.model.mno_response.Freeflow;
import com.app.ussd.model.mno_response.ResponseCleanUp;
import com.app.ussd.model.mno_response.ResponsePull;

@Service
@Transactional
public class FormattageService {
	
		


	    public  Document parseText(String zText) {
	        try
	        {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(new InputSource(new StringReader(zText)));
	            doc.getDocumentElement().normalize();
	            return doc;
	        }
	        catch (Exception e) {
	                e.printStackTrace();
	        }
	        return null;
	    }
	    
	    
	    public String ResPullToXML(ResponsePull responsePull) {
	        String xmlString = "";
	        try {
	            JAXBContext context = JAXBContext.newInstance(ResponsePull.class);
	            Marshaller m = context.createMarshaller();

	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	            StringWriter sw = new StringWriter();
	            m.marshal(responsePull, sw);
	            xmlString = sw.toString();

	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }

	        return xmlString;
	    }
	    
	    public String ResCleanToXML(ResponseCleanUp responseclean) {
	        String xmlString = "";
	        try {
	            JAXBContext context = JAXBContext.newInstance(RequestCleanUp.class);
	            Marshaller m = context.createMarshaller();

	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

	            StringWriter sw = new StringWriter();
	            m.marshal(responseclean, sw);
	            xmlString = sw.toString();

	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }

	        return xmlString;
	    }

	    
	    
	    // Fonction is to set Response parameters with appropriate value
	      public ResponsePull setResponse(RequestPull request,String menu, boolean answerRequired ) {
	    	  
	    	    Freeflow freeflow = new Freeflow();
	    	    freeflow.setMode(request.getFreeflow().getMode());
	    	    // no charged to end User
	    	    freeflow.setFreeflowCharging("N");
	    	    // amount of chargin
	    	    freeflow.setFreeflowChargingAmount("0");
	    	    if(answerRequired) {
	    	          freeflow.setFreeflowState("FC");
	    	     }
	    	    else {	
	    	    	freeflow.setFreeflowState("FB");
	    	     }
	    	  
	    	    ResponsePull responsePull = new ResponsePull();
		    	responsePull.setMsisdn(request.getMsisdn());	    	
		    	responsePull.setApplicationResponse(menu);
		    	responsePull.setFreeflow(freeflow);
	    	  
	    	  return responsePull;
	      }
}
