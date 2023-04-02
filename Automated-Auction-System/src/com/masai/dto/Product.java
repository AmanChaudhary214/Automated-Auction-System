package com.masai.dto;

import java.time.LocalDate;

public interface Product {
	
	public String getPrductId();

	public void setPrductId(String prductId);

	public String getProductName();

	public void setProductName(String productName);

	public String getProductDesc();

	public void setProductDesc(String productDesc);

	public int getPrice();

	public void setPrice(int price);

	public int getQuantity();

	public void setQuantity(int quantity);

	public String getCategoryId();

	public void setCategoryId(String categoryId);

	public LocalDate getMfgDate();

	public void setMfgDate(LocalDate mfgDate);

	public double getGST();

	public void setGST(double gST);

	public String getSold_status();

	public void setSold_status(String sold_status);
	
	public String toString();
	
}
