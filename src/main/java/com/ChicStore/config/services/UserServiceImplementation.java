package com.ChicStore.config.services;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.User;
import com.ChicStore.config.JwdProvider;
import com.ChicStore.config.Dao.UserDao;
import com.ChicStore.config.exception.UserException;
@Service
public class UserServiceImplementation implements UserService{

	private UserDao userDao;
	private JwdProvider jwtProvider;
	
	
	public UserServiceImplementation(UserDao userDao, JwdProvider jwtProvider) {
		
		this.userDao = userDao;
		this.jwtProvider = jwtProvider;
	}

	@Override
	public User findUserById(int userId) throws UserException {
		Optional<User> user = userDao.findById(userId);
		
		if(user.isPresent())
		{
			return user.get();
		}
		throw new UserException("user not found with id: " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userDao.findByEmail(email);
		
		if(user == null)
		{
			throw new UserException("user not found with email " + email);
		}
		return user;
	}

}
