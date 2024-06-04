package com.ChicStore.config.services;

import com.ChicStore.Project.entity.User;
import com.ChicStore.config.exception.UserException;

public interface UserService {
	
	public User findUserById(int userId) throws UserException;

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	
}
