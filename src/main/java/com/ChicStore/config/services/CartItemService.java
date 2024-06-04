package com.ChicStore.config.services;

import com.ChicStore.Project.entity.Cart;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.AddItemRequest;

public interface CartItemService {
	public Cart createCart(User userId);
	
	public String addCartItem(int userId, AddItemRequest req) throws ProductException;
	public Cart findUserCart(int userId);
}
