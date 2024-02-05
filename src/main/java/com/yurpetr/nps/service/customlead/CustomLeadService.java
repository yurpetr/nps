package com.yurpetr.nps.service.customlead;


import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javastream.service.LeadService;
import com.javastream.uriParamsCreator.UriParamsCreator;

public class CustomLeadService extends LeadService {
   private Logger logger = LoggerFactory
         .getLogger(CustomLeadService.class);

   private final static String ADD_METHOD = "crm.lead.add.json";

   public void addNewLead(CustomLead lead) {
      logger.info("Request: Add a new lead: {}", lead.getTitle());
      try {
         UriParamsCreator params = new CustomParamLeadUtils()
               .getParamsForAddLead(lead);
         CustomPushRunner.post(params, ADD_METHOD);
      } catch (UnsupportedEncodingException e) {
         logger.error("An error occurred while adding new lead", e);
      }
   }

}
