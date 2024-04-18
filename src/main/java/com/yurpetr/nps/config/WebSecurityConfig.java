package com.yurpetr.nps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yurpetr.nps.repo.UserRepository;
import com.yurpetr.nps.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
   private static final String[] GET_PUBLIC_URLS = { "/login",
         "/css/**", "/fonts/**", "/js/**", "/webjars/**",
         "/nps.webmanifest", "/favicon.ico", "/robots.txt" };
   private static final String[] POST_PUBLIC_URLS = { "/home",
         "/logout", "/logoutpage", "/password_reset" };

   @Autowired
   private UserDetailsService userService;

   public void setUserService(UserDetailsService userService) {
      this.userService = userService;
   }

   @Bean
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/h2/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.requestMatchers(HttpMethod.GET, GET_PUBLIC_URLS).permitAll()
				.requestMatchers(HttpMethod.POST, POST_PUBLIC_URLS).permitAll()
				.requestMatchers("/admin**").hasAnyRole("ADMIN")
				.requestMatchers("/users**").hasAnyRole("ADMIN")
				.requestMatchers("/justdoit**").hasAnyRole("USER")
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginPage("/login").permitAll()
						.defaultSuccessUrl("/")
						.failureUrl("/login?error=true"))
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
						.logoutSuccessUrl("/"))
				.rememberMe(me -> {
					me.userDetailsService(userService);
					me.tokenRepository(persistentTokenRepository());
				});
		http.csrf(csrf -> csrf
				.ignoringRequestMatchers("/h2/**"));
		http.headers(headers -> headers
				.frameOptions(customizer -> customizer
						.sameOrigin()));
      return http.build();
   }

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Bean
   DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setPasswordEncoder(passwordEncoder);
      authenticationProvider.setUserDetailsService(userService);
      return authenticationProvider;
   }

   @Autowired
   private DataSource dataSource;

   @Bean
   PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl tokenRepo = new JdbcTokenRepositoryImpl();
      tokenRepo.setDataSource(dataSource);
      return tokenRepo;
   }

   @Autowired
   private UserRepository userRepository;

   @Bean
   protected UserDetailsService userDetailsService() {
      return new UserServiceImpl(userRepository);
   }

}