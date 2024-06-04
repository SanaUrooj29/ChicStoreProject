package com.ChicStore.config.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ChicStore.Project.entity.Product;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.CreateProductRequest;

public interface ProductService {

		public Product createProduct(CreateProductRequest req);
		
		public String deleteProduct(int productId) throws ProductException;
		
		public Product updateProduct(int productId, Product req) throws ProductException;
		
		public Product findProductById(int productId) throws ProductException;
		
		public List<Product>  findProductByCategory(String category);
		
		public Page<Product>getAllproduct(String category, List<String>colors,List<String>sizes, Integer minPrice, Integer maxPrice
				, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
}
