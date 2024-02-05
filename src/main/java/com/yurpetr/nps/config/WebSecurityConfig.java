package com.yurpetr.nps.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.yurpetr.nps.repo.UsersRepository;
import com.yurpetr.nps.service.UserServiceImpl;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   private static final String[] GET_PUBLIC_URLS = {
         "/login",
         "/css/**",
         "/fonts/**",
         "/js/**",
         "/webjars/**",
         "/nps.webmanifest"
   };
   private static final String[] POST_PUBLIC_URLS = {
         "/home",
         "/logout",
         "/logoutpage",
         "/password_reset"
   };

   @Autowired
   private UserDetailsService userService;

   public void setUserService(UserServiceImpl userService) {
      this.userService = userService;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests(requests -> requests
                       .antMatchers("/h2/**").permitAll()
                       .antMatchers("/h2-console/**").permitAll()
                       .antMatchers(HttpMethod.GET, GET_PUBLIC_URLS).permitAll()
                       .antMatchers(HttpMethod.POST, POST_PUBLIC_URLS).permitAll()
                       .antMatchers("/admin**").hasAnyRole("ADMIN")
                       .anyRequest().authenticated())
               .formLogin(login -> login.loginPage("/login").permitAll()
                       .defaultSuccessUrl("/")
                       .failureUrl("/login?error=true"))
               .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                       .permitAll()
                       .logoutSuccessUrl("/"))
               .rememberMe(me -> me.tokenRepository(persistentTokenRepository()))
       ;
       http.csrf(csrf -> csrf.ignoringAntMatchers("/h2/**"));
       http.headers(headers -> headers.frameOptions().sameOrigin());

   }

   @Autowired
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setPasswordEncoder(passwordEncoder());
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
   private UsersRepository usersRepository;

   @Bean
   public UserDetailsService userDetailsService() {
      return new UserServiceImpl(usersRepository);
   }

}