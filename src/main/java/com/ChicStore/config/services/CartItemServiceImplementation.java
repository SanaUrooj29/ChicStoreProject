package com.ChicStore.config.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Cart;
import com.ChicStore.Project.entity.CartItem;
import com.ChicStore.Project.entity.Product;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.CartDao;
import com.ChicStore.config.Dao.CartItemDao;
import com.ChicStore.config.exception.CartItemException;
import com.ChicStore.config.exception.UserException;

@Service
public class CartItemServiceImplementation implements CartItemService{

	private CartItemDao cartItemDao;
	private UserService userservice;
	private CartDao cartDao;
	
	public CartItemServiceImplementation(CartItemDao cartItemDao, UserService userservice, CartDao cartDao) {
		super();
		this.cartItemDao = cartItemDao;
		this.userservice = userservice;
		this.cartDao = cartDao;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice()*cartItem.getQuantity());
		
		CartItem createdCartItem = cartItemDao.save(cartItem);
		return createdCartItem;
		
	}

	@Override
	public CartItem updateCartItem(int userId, int id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item = findCartItemById(id);
		User user = userservice.findUserById(item.getUserId());
		
		if(user.getId() == userId)
		{
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getPrice());
			item.setDiscountPrice((item.getProduct().getDiscountPrice()* item.getQuantity()));
		}
		
		return cartItemDao.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, int userId) {
		CartItem cartItem = cartItemDao.isCartItemExist(product, size, userId);
		return cartItem;
		
	}

	@Override
	public void removeCartItem(int userId, int cartItemId) throws CartItemException, UserException {
		CartItem cartItem = findCartItemById(cartItemId);
		User user = userservice.findUserById(cartItem.getUserId());
		User reqUser = userservice.findUserById(userId);
		
		if(user.getId() ==reqUser.getId())
		{
			cartItemDao.deleteById(cartItemId);
		}
		else
		{
			throw new UserException("You Can't remove another users item");
		}
	}

	@Override
	public CartItem findCartItemById(int cartItemId) throws CartItemException {
		Optional<CartItem> opt = cartItemDao.findById(cartItemId);
		
		if(opt.isPresent())
		{
			return opt.get();
		}
		throw new CartItemException("cartItem not found with id: " + cartItemId);
	}

}
