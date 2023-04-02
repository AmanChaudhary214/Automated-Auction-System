package com.masai.dto;

import java.time.LocalDate;

public class TransactionImpl implements Transaction{

	private String transactionId;
	private String productId;
	private String categoryId;
	private String buyerName;
	private String sellerName;
	private LocalDate transactionDate;
	
	public TransactionImpl(String transactionId, String productId, String categoryId, String buyerName,
			String sellerName, LocalDate transactionDate) {
		super();
		this.transactionId = transactionId;
		this.productId = productId;
		this.categoryId = categoryId;
		this.buyerName = buyerName;
		this.sellerName = sellerName;
		this.transactionDate = transactionDate;
	}

	public TransactionImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "TransactionImpl [transactionId=" + transactionId + ", productId=" + productId + ", categoryId="
				+ categoryId + ", buyerName=" + buyerName + ", sellerName=" + sellerName + ", transactionDate="
				+ transactionDate + "]";
	}
	
}
