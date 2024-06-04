package com.ChicStore.config.services;

import com.ChicStore.Project.entity.Cart;
import com.ChicStore.Project.entity.CartItem;
import com.ChicStore.Project.entity.Product;
import com.ChicStore.config.exception.CartItemException;
import com.ChicStore.config.exception.UserException;

public interface CartItemService {
	public CartItem createCartItem(CartItem cartItem);
	public CartItem updateCartItem(int userid, int id, CartItem cartItem) throws CartItemException, UserException;
	public CartItem isCartItemExist(Cart cart, Product product, String sixe, int userId);
	public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException;
	public CartItem findCartItemById(int cartItem) throws CartItemException;
}
