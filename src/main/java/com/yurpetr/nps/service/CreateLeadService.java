/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yurpetr.nps.service.customlead.CustomClient;
import com.yurpetr.nps.service.customlead.CustomLead;

public class CreateLeadService {
   private static final Logger LOGGER = LoggerFactory
         .getLogger(CreateLeadService.class);

   private CustomClient client;
   private CustomLead lead;

   public void createLead(String opinion, String point) {

      LOGGER.info("Creating lead");
      client = new CustomClient(getToken(), "portal.trade-liga.com",
            1);
      lead = new CustomLead();
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

}
