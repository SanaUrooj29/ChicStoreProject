package com.ChicStore.config.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ChicStore.Project.entity.Category;

public interface CategoryDao extends JpaRepository<Category,Integer>{
	
	public Category findByName(String name);
	
	@Query("Select c FROM Category c WHERE c.name=:name AND c.parentCategory.name=:parentCategoryName")
	public Category findNameAndParent(@Param("name") String name, 
			@Param("parentCategoryName") String parentCategoryName);
}
