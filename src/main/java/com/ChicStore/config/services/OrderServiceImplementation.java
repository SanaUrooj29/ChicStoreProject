package com.ChicStore.config.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Address;
import com.ChicStore.Project.entity.Order;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.CartDao;
import com.ChicStore.config.exception.OrderException;

@Service
public class OrderServiceImplementation implements OrderService{
	
	private CartDao cartDao;
	//private Address 
	private CartItemService cartItemService;
	private ProductService productService;
	
	
	public OrderServiceImplementation(CartDao cartDao, CartItemService cartItemService, ProductService productService) {
		//super();
		this.cartDao = cartDao;
		this.cartItemService = cartItemService;
		this.productService = productService;
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderById(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> userOrderHistory(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placeOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shipOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliverOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancelOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
	}

}
