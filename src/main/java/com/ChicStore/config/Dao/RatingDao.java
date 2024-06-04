package com.ChicStore.config.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ChicStore.Project.entity.Ratings;

public interface RatingDao extends JpaRepository<Ratings, Integer>{
	@Query ("SELECT r FROM Rating r WHERE r.product.id = :productId")
	public List<Ratings> getAllProductsRating(@Param("productId")Integer productId);
}
