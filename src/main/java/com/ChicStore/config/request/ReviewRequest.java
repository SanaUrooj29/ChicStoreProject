package com.ChicStore.config.request;

public class ReviewRequest {
	private int productId;
	private String review;
	public ReviewRequest() {
		super();
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
}
