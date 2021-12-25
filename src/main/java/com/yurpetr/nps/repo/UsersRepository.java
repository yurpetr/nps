package com.yurpetr.nps.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurpetr.nps.model.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	Optional<User> findByPointOfSale(String pointOfSale);

	Optional<User> findByLogin(String login);

}
