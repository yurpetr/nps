/**
 * Файл создан Юркой Петровым на его компе Nov 14, 2021 и это часть
 * проекта crm
 */

package com.yurpetr.nps.service.customlead;


import com.javastream.entity.Lead;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class CustomLead extends Lead {
   private String UF_CRM_1636842898;
   private String UF_CRM_1636848516;

   public void setOpinion(String opinion) {
      this.UF_CRM_1636842898 = opinion;
   }

   public void setPoint(String point) {
      this.UF_CRM_1636848516 = point;
   }

   public void setResponsible(String responsible) {
      this.setAssignedById(responsible);
   }

}
