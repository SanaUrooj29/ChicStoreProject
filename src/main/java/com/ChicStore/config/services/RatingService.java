package com.ChicStore.config.services;

import java.util.List;

import com.ChicStore.Project.entity.Ratings;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.RatingDao;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.RatingRequest;

public interface RatingService {
	public Ratings createRating(RatingRequest ratingRequest, User user ) throws ProductException;
	public List<RatingDao> getProductRating(int productId);

}
