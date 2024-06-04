package com.ChicStore.config.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ChicStore.Project.entity.Category;
import com.ChicStore.Project.entity.Product;
import com.ChicStore.config.Dao.CategoryDao;
import com.ChicStore.config.Dao.ProductRepository;
import com.ChicStore.config.exception.ProductException;
import com.ChicStore.config.request.CreateProductRequest;
@Service
public class ProductServiceImplementation implements ProductService{

	
	private ProductRepository productDao;
	private UserService userService;
	private CategoryDao categoryDao;
	
	
	public ProductServiceImplementation(ProductRepository productDao,
			UserService userService, CategoryDao categoryDao) {
		super();
		this.productDao = productDao;
		this.userService = userService;
		this.categoryDao = categoryDao;
	}

	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel = categoryDao.findByName(req.getToplevelCategory()); 
		if(topLevel == null)
		{
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getToplevelCategory());
			topLevelCategory.setLevel(1);
			
			topLevel = categoryDao.save(topLevelCategory);
		}
		
		Category secondLevel = categoryDao.findNameAndParent(req.getSecondlevelCategory(), topLevel.getName()); 
		if(secondLevel == null)
		{
			Category secondLevelCategory = new Category();
			secondLevelCategory.setName(req.getSecondlevelCategory());
			secondLevelCategory.setLevel(2);
			
			secondLevel = categoryDao.save(secondLevelCategory);
		}
		
		Category thirdLevel = categoryDao.findNameAndParent(req.getThirdlevelCategory(), secondLevel.getName()); 
		if(thirdLevel == null)
		{
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdlevelCategory());
			thirdLevelCategory.setLevel(2);
			
			thirdLevel = categoryDao.save(thirdLevelCategory);
		}
		
		Product prod = new Product();
		prod.setTitle(req.getTitle());
		prod.setColor(req.getColor());
		prod.setDescription(req.getDescription());
		prod.setDiscountPrice(req.getDiscountedPrice());
		prod.setDiscountPresent(req.getDiscountPresent());
		prod.setImageUrl(req.getImageUrl());
		prod.setBrand(req.getBrand());
		prod.setPrice(req.getPrice());
		prod.setSizes(req.getSize());
		prod.setQuantity(req.getQuality());
		prod.setCategory(thirdLevel);
		prod.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct = productDao.save(prod);
		return savedProduct;
	}

	@Override
	public String deleteProduct(int productId) throws ProductException {
		Product prod = findProductById(productId);
		
		prod.getSizes().clear();
		productDao.delete(prod);
		return "Product Deleted Successfully";
		
	}

	@Override
	public Product updateProduct(int productId, Product req) throws ProductException {
		Product prod = findProductById(productId);
		
		if(req.getQuantity() != 0)
		{
			prod.setQuantity(req.getQuantity());
		}
		return productDao.save(prod);
	}

	@Override
	public Product findProductById(int id) throws ProductException {
	    Optional<Product> opt = productDao.findById(id);
		
	    if(opt.isPresent())
	    {
	    	return opt.get();
	    }
	    throw new ProductException("Prouct not found with id- " + id);
	    
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllproduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);		
		
		List<Product> products = productDao.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		if(!colors.isEmpty()) {
			products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		
		if(stock != null)
		{
			if(stock.equals("in_stock"))
			{
				products= products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList()); 
			}				
			else if(stock.equals("out_of_stock"))
			{
				products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
		}
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
		
		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable,products.size());
		 
		return  filteredProducts;
	}

}
