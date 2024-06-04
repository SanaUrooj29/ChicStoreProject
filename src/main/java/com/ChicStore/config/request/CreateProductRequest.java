package com.ChicStore.config.request;

import java.util.HashSet;
import java.util.Set;

import com.ChicStore.Project.entity.Size;

public class CreateProductRequest {
	private String title;
	private String description;
	private int price;
	
	private int discountedPrice;
	
	private int discountPresent;
	
	private int quality;
	
	private String brand;
	
	private String color;
	
	private Set<Size> size = new HashSet<>();
	
	private String imageUrl;
	
	private String toplevelCategory;
	private String secondlevelCategory;
	private String thirdlevelCategory;
	public CreateProductRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateProductRequest(String title, String description, int price, int discountedPrice, int discountPresent,
			int quality, String brand, String color, Set<Size> size, String imageUrl, String toplevelCategory,
			String secondlevelCategory, String thirdlevelCategory) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountPresent = discountPresent;
		this.quality = quality;
		this.brand = brand;
		this.color = color;
		this.size = size;
		this.imageUrl = imageUrl;
		this.toplevelCategory = toplevelCategory;
		this.secondlevelCategory = secondlevelCategory;
		this.thirdlevelCategory = thirdlevelCategory;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public int getDiscountPresent() {
		return discountPresent;
	}
	public void setDiscountPresent(int discountPresent) {
		this.discountPresent = discountPresent;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Set<Size> getSize() {
		return size;
	}
	public void setSize(Set<Size> size) {
		this.size = size;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getToplevelCategory() {
		return toplevelCategory;
	}
	public void setToplevelCategory(String toplevelCategory) {
		this.toplevelCategory = toplevelCategory;
	}
	public String getSecondlevelCategory() {
		return secondlevelCategory;
	}
	public void setSecondlevelCategory(String secondlevelCategory) {
		this.secondlevelCategory = secondlevelCategory;
	}
	public String getThirdlevelCategory() {
		return thirdlevelCategory;
	}
	public void setThirdlevelCategory(String thirdlevelCategory) {
		this.thirdlevelCategory = thirdlevelCategory;
	}

}
