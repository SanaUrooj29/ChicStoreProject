package com.ChicStore.config.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.UserDao;

@Service
public class CustomeUserServiceImplementation implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	public CustomeUserServiceImplementation(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findByEmail(String username) {
		return userDao.findByEmail(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found with email - " + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

	// method to insert book
	public User save(User user) {
		return userDao.save(user);
	}

}
