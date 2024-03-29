package com.yurpetr.nps.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yurpetr.nps.model.Role;
import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   UserRepository usersRepo;

   public UserServiceImpl(UserRepository usersRepo) {
      super();
      this.usersRepo = usersRepo;
   }

   public Optional<User> findByLogin(String login) {
      return usersRepo.findByLogin(login);
   }

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String username)
         throws UsernameNotFoundException {
      Optional<User> user = findByLogin(username);
      if (user == null) {
         throw new UsernameNotFoundException(
               String.format("Користувача '%s' не знайдено", username));
      }
      return new org.springframework.security.core.userdetails.User(
            user.get().getLogin(), user.get().getPassword(),
            mapRolesToAuthorities(user.get().getRoles()));
   }

   private Collection<? extends GrantedAuthority> mapRolesToAuthorities(
         Collection<Role> roles) {
      return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName()))
            .collect(Collectors.toList());
   }
}
