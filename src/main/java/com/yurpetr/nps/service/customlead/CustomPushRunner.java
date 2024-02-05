package com.yurpetr.nps.service.customlead;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javastream.configs.Settings;
import com.javastream.uriParamsCreator.UriParamsCreator;

public class CustomPushRunner {

   private static Logger logger = LoggerFactory
         .getLogger(CustomPushRunner.class);

   private final static String HTTPS_ADDRESS = "https://";
   private final static String SLASH_PATTERN = "/";
   private final static String REST_FIELD = "rest";

   public static void post(UriParamsCreator params, String metod) {
      HttpClient httpClient = HttpClientBuilder.create().build();

      String url = HTTPS_ADDRESS + getAccount() + SLASH_PATTERN
            + REST_FIELD + SLASH_PATTERN + getRestID() + SLASH_PATTERN
            + getToken() + SLASH_PATTERN + metod + params;

      HttpPost request = new HttpPost(url);
      request.addHeader("content-type", "application/json");

      try {
         HttpResponse response = httpClient.execute(request);
         logger.info("Request {}, status of response: {}", metod,
               response.getStatusLine().getStatusCode());

         JSONObject jsonObj = new JSONObject(
               EntityUtils.toString(response.getEntity()));

         logger.info("ID of created Lead: {}",
               jsonObj.has("result") ? jsonObj.opt("result")
                     : jsonObj.toString());
      } catch (Exception e) {
         logger.error(
               "An error occurred while getting HttpResponse, method: {}",
               metod, e);
      }

   }

   private static String getToken() {
      return Settings.getToken();
   }

   private static String getAccount() {
      return Settings.getAccount();
   }

   private static String getRestID() {
      return Settings.getRestID().toString();
   }

}
