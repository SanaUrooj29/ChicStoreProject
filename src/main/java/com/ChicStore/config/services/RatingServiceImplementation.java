package com.ChicStore.config.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Product;
import com.ChicStore.Project.entity.Ratings;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.RatingDao;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService{

	private RatingDao ratingDao;
	private ProductService productService;
	
	
	public RatingServiceImplementation(RatingDao ratingDao, ProductService productService) {
		this.ratingDao = ratingDao;
		this.productService = productService;
	}

	@Override
	public Ratings createRating(RatingRequest req, User user) throws ProductException {
		Product prduct = productService.findProductById(req.getProductId());
		
		Ratings rating = new Ratings();
		rating.setProduct(prduct);
		rating.setUser(user);
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingDao.save(rating);
	}

	@Override
	public List<RatingDao> getProductRating(int productId) {
		
		return null;
	}

}
