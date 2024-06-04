package com.ChicStore.config.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Product;
import com.ChicStore.Project.entity.Reviews;
import com.ChicStore.Project.entity.User;
import com.ChicStore.config.Dao.ProductRepository;
import com.ChicStore.config.Dao.ReviewDao;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService{
	private ReviewDao reviewDao;
	private ProductService productService;
	private ProductRepository productDao;
	
	public ReviewServiceImplementation(ReviewDao reviewDao, ProductService productService,
			ProductRepository productDao) {
		this.reviewDao = reviewDao;
		this.productService = productService;
		this.productDao = productDao;
	}

	@Override
	public Reviews createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		
		Reviews review = new Reviews();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		return reviewDao.save(review);
	}

	@Override
	public List<Reviews> getAllReview(int productId) {
		
		return reviewDao.getAllProductsReview(productId);
	}

}
