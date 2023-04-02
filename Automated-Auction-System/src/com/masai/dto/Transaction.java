package com.masai.dto;

import java.time.LocalDate;

public interface Transaction {

	public String getTransactionId();

	public void setTransactionId(String transactionId);

	public String getProductId();

	public void setProductId(String productId);

	public String getCategoryId();

	public void setCategoryId(String categoryId);

	public String getBuyerName();

	public void setBuyerName(String buyerName);

	public String getSellerName();

	public void setSellerName(String sellerName);

	public LocalDate getTransactionDate();

	public void setTransactionDate(LocalDate transactionDate);
	
	public String toString();
	
}
