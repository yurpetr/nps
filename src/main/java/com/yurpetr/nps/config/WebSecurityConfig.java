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
            .authorizeRequests()
            .antMatchers("/h2/**").permitAll()
            .antMatchers(HttpMethod.GET, GET_PUBLIC_URLS).permitAll()
            .antMatchers(HttpMethod.POST, POST_PUBLIC_URLS).permitAll()
            .antMatchers("/admin**").hasAnyRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll()
            .defaultSuccessUrl("/")
            .failureUrl("/login?error=true")
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .permitAll()
            .logoutSuccessUrl("/")
            .and()
            .rememberMe().tokenRepository(persistentTokenRepository());
      http.csrf().ignoringAntMatchers("/h2/**");
      http.headers().frameOptions().sameOrigin();

   }

   @Autowired
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
      authenticationProvider.setPasswordEncoder(passwordEncoder());
      authenticationProvider.setUserDetailsService(userService);
      return authenticationProvider;
   }

   @Autowired
   private DataSource dataSource;

   @Bean
   public PersistentTokenRepository persistentTokenRepository() {
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