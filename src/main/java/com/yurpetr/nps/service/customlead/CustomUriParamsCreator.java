package com.yurpetr.nps.service.customlead;

import java.math.BigDecimal;

import com.javastream.uriParamsCreator.UriParamsCreator;

public class CustomUriParamsCreator extends UriParamsCreator {

   @Override
   public void put(String key, String value) {
      if (value != "%20")
         super.put(key, value);
   }

   @Override
   public void put(String key, Integer value) {
      super.put(key, value);
   }

   @Override
   public void put(String key, Double value) {
      super.put(key, value);
   }

   @Override
   public void put(String key, BigDecimal value) {
      super.put(key, value);
   }

}
