/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service.customlead;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.javastream.uriParamsCreator.UriParamsCreator;
import com.javastream.utils.lead.ParamLeadUtils;

public class CustomParamLeadUtils extends ParamLeadUtils {

   private final static String SPACE_PARAM = "%20";
   private final static String AMPERSAND_PATTERN = "&";
   private final static String OPINION = "FIELDS[UF_CRM_1636842898]";
   private final static String POINT = "FIELDS[UF_CRM_1636848516]";
   private final static String UTM_SOURCE = "FIELDS[UTM_SOURCE]";

   public UriParamsCreator getParamsForAddLead(CustomLead lead)
         throws UnsupportedEncodingException {
      UriParamsCreator params = super.addMethod(lead);
      params.builder.append(AMPERSAND_PATTERN);
      params.put(UTM_SOURCE, check(lead.getUtmSource().toString()));
      params.put(OPINION, check(lead.getOpinion().toString()));
      params.put(POINT, check(lead.getPoint().toString()));
      params.build();
      return params;
   }

   private String check(String field) {
      if (field == null) {
         return SPACE_PARAM;
      } else {
         return URLEncoder.encode(field);
      }
   }

}
/*
 * public UriParamsCreator getParamsForAddLead(Lead lead) throws
 * UnsupportedEncodingException { UriParamsCreator params =
 * initialFieldsForAddLead(lead); params.build(); return params; }
 */