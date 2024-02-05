package com.yurpetr.nps;

import org.h2.server.web.WebServer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
   public static void main(String[] args) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(
            12);
      String rawPassword = "password";
      String encodedPassword = passwordEncoder.encode(rawPassword);
      System.out
            .println("Encrypted user password: " + encodedPassword);

      rawPassword = "passwordpassword";
      String encodedAdminPassword = WebServer
            .encodeAdminPassword(rawPassword);
      System.out.println("Encrypted H@ console admin password: "
            + encodedAdminPassword);
   }

}
