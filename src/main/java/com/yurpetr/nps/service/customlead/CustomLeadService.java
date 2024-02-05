/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service.customlead;


import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javastream.service.LeadService;
import com.javastream.uriParamsCreator.UriParamsCreator;
import com.javastream.utils.PushRunner;

public class CustomLeadService extends LeadService {
   private Logger logger = LoggerFactory
         .getLogger(CustomLeadService.class);

   private final static String ADD_METHOD = "crm.lead.add";
   // private final static String GET_METHOD = "crm.lead.get";
   // private final static String DELETE_METHOD = "crm.lead.delete";
   // private final static String UPDATE_METHOD = "crm.lead.update";

   public void addNewLead(CustomLead lead) {
      logger.info("Request: Add a new lead: {}", lead.getId());
      try {
         UriParamsCreator params = new CustomParamLeadUtils()
               .getParamsForAddLead(lead);
         PushRunner.post(params, ADD_METHOD);
      } catch (UnsupportedEncodingException e) {
         logger.error("An error occurred while adding new lead", e);
      }
   }

}
