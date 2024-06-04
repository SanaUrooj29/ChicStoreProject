package com.ChicStore.config.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ChicStore.Project.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{
	
	@Query("SELECT c FROM Cart c WHERE c.userId = :userId")
	public Cart findByUserId(@Param("userId") int userId);
}
