/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service.customlead;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.yurpetr.javastream.uriParamsCreator.UriParamsCreator;
import com.yurpetr.javastream.utils.lead.ParamLeadUtils;


public class CustomParamLeadUtils extends ParamLeadUtils {

   private final static String SPACE_PARAM = "%20";
   private final static String AMPERSAND_PATTERN = "&";
   private final static String UF_CRM_1636842898 = "FIELDS[UF_CRM_1636842898]";
   private final static String UF_CRM_1636848516 = "FIELDS[UF_CRM_1636848516]";
   private final static String UTM_SOURCE = "FIELDS[UTM_SOURCE]";
   

   public UriParamsCreator getParamsForAddLead(CustomLead lead)
         throws UnsupportedEncodingException {
      UriParamsCreator params = super.getParamsForAddLead(lead);
      params.builder.append(AMPERSAND_PATTERN);
      params.put(UTM_SOURCE, check(lead.getUTM_SOURCE().toString()));
      params.put(UF_CRM_1636842898,
            check(lead.getUF_CRM_1636842898().toString()));
      params.put(UF_CRM_1636848516,
            check(lead.getUF_CRM_1636848516().toString()));
      params.build();
      return params;
   }

   @SuppressWarnings("deprecation")
   private String check(String field) {
      if (field == null) {
         return SPACE_PARAM;
      } else {
         return URLEncoder.encode(field.toString());
      }
   }

}
/*
 * public UriParamsCreator getParamsForAddLead(Lead lead) throws
 * UnsupportedEncodingException { UriParamsCreator params =
 * initialFieldsForAddLead(lead); params.build(); return params; }
 */