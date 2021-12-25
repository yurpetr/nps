/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service.customlead;

import com.yurpetr.javastream.Client;

public class CustomClient extends Client {

   public CustomClient(String token, String account,
         Integer rest_ID) {
      super(token, account, rest_ID);
   }

   public CustomLeadService getLeadService() {
      return new CustomLeadService();
   }
}
