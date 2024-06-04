package com.ChicStore.config.services;

import java.util.List;

import com.ChicStore.Project.entity.Reviews;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.ReviewRequest;

public interface ReviewService {
	public Reviews createReview(ReviewRequest req, User user) throws ProductException;
	public List<Reviews> getAllReview(int productId);
}
