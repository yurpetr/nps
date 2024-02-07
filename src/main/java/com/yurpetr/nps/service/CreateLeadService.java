package com.yurpetr.nps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yurpetr.nps.service.customlead.CustomClient;
import com.yurpetr.nps.service.customlead.CustomLead;

public class CreateLeadService {
   private static final Logger LOGGER = LoggerFactory
         .getLogger(CreateLeadService.class);

   public void createLead(String opinion, String point) {

      LOGGER.info("Creating lead");
      CustomClient client = new CustomClient(getToken(),
            getPortalUrl(), getRestId());
      CustomLead lead = new CustomLead();
      lead.setTitle("Оцінка обслуговування");
      lead.setStatusId("NEW");
      lead.setOpportunity("0"); // Сума
      lead.setCurrencyId("UAH");
      lead.setSourceId("20");   // Джерело ліда
      lead.setUtmSource("npsp");
      lead.setResponsible("8"); // Відповідальний ID, 8 - Andron, 1 -
                                // Admin
      lead.setPoint(point);
      lead.setOpinion(opinion);
      client.customLeadService().addNewLead(lead);
   }

   private String getToken() {

      return System.getenv("BX_TOKEN");
   }

   private String getPortalUrl() {

      return System.getenv("PORTAL_URL");
   }

   private Integer getRestId() {

      return Integer.parseInt(System.getenv("REST_ID"));
   }

}
