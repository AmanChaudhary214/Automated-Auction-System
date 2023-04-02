package com.masai.dto;

import java.time.LocalDate;

public class ProductImpl implements Product{

	private String prductId;
	private String productName;
	private String productDesc;
	private int price;
	private int quantity;
	private String categoryId;
	private LocalDate mfgDate;
	private double GST;
	private String sold_status;
	
	public ProductImpl(String prductId, String productName, String productDesc, int price, int quantity,
			String categoryId, LocalDate mfgDate, double gST, String sold_status) {
		super();
		this.prductId = prductId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.price = price;
		this.quantity = quantity;
		this.categoryId = categoryId;
		this.mfgDate = mfgDate;
		this.GST = gST;
		this.sold_status = sold_status;
	}

	public ProductImpl(String productName2, String productDesc2, int price2, int quantity2, String categoryId2,
			LocalDate mfgDate2, double gst2, String sold_status2) {
		// TODO Auto-generated constructor stub
	}

	public ProductImpl() {
		// TODO Auto-generated constructor stub
	}

	public String getPrductId() {
		return prductId;
	}

	public void setPrductId(String prductId) {
		this.prductId = prductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public LocalDate getMfgDate() {
		return mfgDate;
	}

	public void setMfgDate(LocalDate mfgDate) {
		this.mfgDate = mfgDate;
	}

	public double getGST() {
		return GST;
	}

	public void setGST(double gST) {
		GST = gST;
	}

	public String getSold_status() {
		return sold_status;
	}

	public void setSold_status(String sold_status) {
		this.sold_status = sold_status;
	}

	@Override
	public String toString() {
		return "ProductImpl [prductId=" + prductId + ", productName=" + productName + ", productDesc=" + productDesc
				+ ", price=" + price + ", quantity=" + quantity + ", categoryId=" + categoryId + ", mfgDate=" + mfgDate
				+ ", GST=" + GST + ", sold_status=" + sold_status + "]";
	}
	
}
