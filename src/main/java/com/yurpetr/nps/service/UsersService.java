package com.yurpetr.nps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.UserRepository;

@Service
public class UsersService implements IUser {

	@Autowired
	UserRepository usersRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UsersService(UserRepository usersRepo) {
		this.usersRepo = usersRepo;
	}

	@Override
	public List<User> getAllUsers() {
		return usersRepo.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return usersRepo.findById(id);
	}

	@Override
	public Optional<User> findByPointOfSale(String pointOfSale) {
		return usersRepo.findByPointOfSale(pointOfSale);
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return usersRepo.findByLogin(login);
	}

	@Override
	public User save(User usr) {
		usr.setPassword(passwordEncoder.encode(usr.getPassword()));
		return usersRepo.save(usr);
	}

	@Override
	public void deleteById(int id) {
		usersRepo.deleteById(id);
	}

}
