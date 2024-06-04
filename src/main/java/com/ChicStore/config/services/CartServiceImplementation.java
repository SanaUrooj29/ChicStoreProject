package com.ChicStore.config.services;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Cart;
import com.ChicStore.Project.entity.CartItem;
import com.ChicStore.Project.entity.Product;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.CartDao;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{	
	
	private CartDao cartDao;
	private CartItemService cartItemService;
	private ProductService productService;
	
	
	public CartServiceImplementation(CartDao cartDao, CartItemService cartItemService, ProductService productService) {
		
		this.cartDao = cartDao;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public String addCartItem(int userId, AddItemRequest req) throws ProductException {
		
		Cart cart = cartDao.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		
		if(isPresent == null)
		{
			CartItem item = new CartItem();
			item.setProduct(product);
			item.setCart(cart);
			item.setQuantity(req.getQuantity());
			item.setUserId(userId);
			
			int price = req.getQuantity()*product.getDiscountPrice(); 
			item.setPrice(price);
			item.setSize(req.getSize());
			
			CartItem createdCartItem = cartItemService.createCartItem(item);
			cart.getCartItems().add(createdCartItem);
		}
		
		return "Item Add To Cart";
	}

	@Override
	public Cart findUserCart(int userId) {
		Cart cart = cartDao.findByUserId(userId);
		
		int totalPrice = 0;
		int totalDiscountPrice = 0;
		int totalitem = 0;
		
		for(CartItem cartItem : cart.getCartItems())
		{
			totalPrice = totalPrice + cartItem.getPrice();
			totalDiscountPrice = totalDiscountPrice  + cartItem.getDiscountPrice();
			totalitem = totalitem + cartItem.getQuantity(); 
		}
		
		cart.setTotalDiscountedPrice(totalDiscountPrice);
		cart.setTotalitem(totalitem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice - totalDiscountPrice);
		return cartDao.save(cart);
	}

	@Override
	public Cart createCart(User userId) {
		Cart cart = new Cart();
		cart.setUser(userId);
		return cartDao.save(cart);
	}

}
