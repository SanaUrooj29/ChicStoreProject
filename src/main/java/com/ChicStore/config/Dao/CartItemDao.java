package com.ChicStore.config.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ChicStore.Project.entity.CartItem;
import com.ChicStore.Project.entity.Product;

public interface CartItemDao extends JpaRepository<CartItem, Integer>{
	
	@Query ("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId = :userId")
	public CartItem isCartItemExist(@Param("product")Product product, @Param("size")String size, @Param("userId")int userId);
}
 