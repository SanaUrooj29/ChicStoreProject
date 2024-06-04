package com.ChicStore.config.services;

import java.util.List;

import com.ChicStore.Project.entity.Address;
import com.ChicStore.Project.entity.Order;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.exception.OrderException;

public interface OrderService {
	
	public Order createOrder(User user, Address shippingAddress);
	public Order findOrderById(int orderId) throws OrderException;
	public List<Order> userOrderHistory(int userId);
	
	public Order placeOrder(int orderId) throws OrderException;
	public Order confirmedOrder(int orderId) throws OrderException;
	public Order shipOrder(int orderId)  throws OrderException;
	public Order deliverOrder(int orderId)  throws OrderException;
	public Order cancelOrder(int orderId)  throws OrderException;
	public List<Order> getAllOrders();
	public void deleteOrder(Long orderId) throws OrderException;
	

}
