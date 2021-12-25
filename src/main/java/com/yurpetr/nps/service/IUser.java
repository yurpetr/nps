package com.yurpetr.nps.service;

import java.util.List;
import java.util.Optional;

import com.yurpetr.nps.model.User;

public interface IUser {
	List<User> getAllUsers();

	Optional<User> findById(int id);

	Optional<User> findByPointOfSale(String pointOfSale);

	Optional<User> findByLogin(String login);
	
	User save(User std);

	void deleteById(int id);

}
