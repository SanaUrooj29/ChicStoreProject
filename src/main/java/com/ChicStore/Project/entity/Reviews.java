package com.ChicStore.Project.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String review;

	@ManyToOne
	@JoinColumn(name = "product_id")
	// to avoid nested problem
	@JsonIgnore
	private Product product;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private LocalDateTime createdAt;

	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(int id, String review, Product product, User user, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.review = review;
		this.product = product;
		this.user = user;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReview()
	{
		return review;
	}
	
	public void setReview(String review)
	{
		this.review = review;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
