package com.ChicStore.config.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ChicStore.Project.entity.Reviews;

public interface ReviewDao extends JpaRepository<Reviews, Integer> {
	public List<Reviews> getAllProductsReview(@Param("productId") Integer productId);
}
