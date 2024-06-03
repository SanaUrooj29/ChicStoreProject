package com.ChicStore.config.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ChicStore.Project.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

	public User findByEmail(String username);

}
