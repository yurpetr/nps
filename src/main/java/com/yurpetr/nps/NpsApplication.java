package com.yurpetr.nps;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NpsApplication extends SpringBootServletInitializer {

   @Override
   protected SpringApplicationBuilder configure(
         SpringApplicationBuilder application) {
      return application.sources(NpsApplication.class);
   }

   public static void main(String[] args) {
      SpringApplication.run(NpsApplication.class, args);

   }

   /**
    * Start internal H2 server so we can query the DB from IDE
    *
    * @return H2 Server instance
    * @throws SQLException
    */
   @Bean(initMethod = "start", destroyMethod = "stop")
   public Server h2Server() throws SQLException {
      return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort",
            "9092");
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

}
