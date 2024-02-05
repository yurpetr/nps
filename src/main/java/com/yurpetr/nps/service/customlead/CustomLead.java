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
   private String opinion;
   private String point;

   public void setOpinion(String opinion) {
      this.opinion = opinion;
   }

   public void setPoint(String point) {
      this.point = point;
   }

   public void setResponsible(String responsible) {
      this.setAssignedById(responsible);
   }

}
