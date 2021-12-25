package com.yurpetr.nps;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	public static void main(String[] args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
		String rawPassword = "password";
		String encodedPassword = passwordEncoder.encode(rawPassword);

		System.out.println(encodedPassword);
	}

}
