package com.ChicStore.config.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChicStore.Project.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
