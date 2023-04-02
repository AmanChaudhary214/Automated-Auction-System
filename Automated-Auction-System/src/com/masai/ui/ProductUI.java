package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.ProductDAO;
import com.masai.dao.ProductDAOImpl;
import com.masai.dto.Product;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.dto.ProductImpl;

public class ProductUI {
	
	private ProductDAO productDAO;
	private Scanner scanner;
	
	public ProductUI(Scanner scanner){
		productDAO = new ProductDAOImpl();
		this.scanner = scanner;
	}
	

	public void viewProducts() throws SomethingWentWrongException, NoRecordFoundException, ClassNotFoundException {
		List<Product> listProducts = productDAO.getAllProducts();
		listProducts.forEach(System.out::println);
	}
	

	public void viewProductsByCategory() throws NoRecordFoundException, SomethingWentWrongException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("Enter category id ");
		String categoryId = scanner.next();
		List<Product> listProducts = productDAO.getProductsByCategoryId(categoryId);
		listProducts.forEach(System.out::println);
	}
	

	public void addProduct() throws SomethingWentWrongException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("Enter productId: ");
		String productId = scanner.next();
		
		System.out.print("Enter product name: ");
		String productName = scanner.next();
		
		System.out.print("Enter product desc: ");
		String productDesc = scanner.next();
		
		System.out.print("Enter price: ");
		int price = scanner.nextInt();
		
		System.out.print("Enter quantity: ");
		int quantity = scanner.nextInt();
		
		System.out.print("Enter categoryId: ");
		String categoryId = scanner.next();
		
		System.out.print("Enter MFG. Date: ");
		LocalDate mfgDate = LocalDate.parse(scanner.next());
		
		System.out.print("Enter GST ");
		Double gst = scanner.nextDouble();
		
		System.out.print("Enter sold status ");
		String sold_status = scanner.next();
		
		//create object for product with all details
		Product product= new ProductImpl(productId, productName, productDesc, price, quantity, categoryId, mfgDate, gst, sold_status);
		
		productDAO.addProduct(product);;
		System.out.println("Product added successfully");
	}
	
	
	public void updateProduct() throws NoRecordFoundException, SomethingWentWrongException, ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.print("Enter productId: ");
		String productId = scanner.next();
		
		System.out.print("Enter product name ");
		String productName = scanner.next();
		
		System.out.print("Enter product desc ");
		String productDesc = scanner.next();
		
		System.out.print("Enter price ");
		int price = scanner.nextInt();
		
		System.out.print("Enter quantity ");
		int quantity = scanner.nextInt();
		
		System.out.print("Enter categoryId ");
		String categoryId = scanner.next();
		
		System.out.print("Enter MFG. Date ");
		LocalDate mfgDate = LocalDate.parse(scanner.next());
		
		System.out.print("Enter GST ");
		Double gst = scanner.nextDouble();
		
		System.out.print("Enter sold status ");
		String sold_status = scanner.next();
		
		//create object for product with all details
		Product product= new ProductImpl(productId, productName, productDesc, price, quantity, categoryId, mfgDate, gst, sold_status);
		
		productDAO.updateProduct(product);
		System.out.println("Product updated successfully");
	}

	
	public void viewSoldProducts() throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		List<Product> listProducts = productDAO.getSoldProducts();
		listProducts.forEach(System.out::println);
	}

	
	public void purchaseProducts() {
		// TODO Auto-generated method stub
		
	}	
	

	public void viewOrders() {
		// TODO Auto-generated method stub
		
	}

}
