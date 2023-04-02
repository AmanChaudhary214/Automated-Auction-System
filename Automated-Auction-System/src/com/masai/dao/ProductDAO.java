package com.masai.dao;

import java.util.List;

import com.masai.dto.Product;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface ProductDAO {

	List<Product> getAllProducts() throws SomethingWentWrongException, ClassNotFoundException;

	List<Product> getProductsByCategoryId(String categoryId) throws NoRecordFoundException, ClassNotFoundException, SomethingWentWrongException;

	void updateProduct(Product product) throws ClassNotFoundException, SomethingWentWrongException;

	void addProduct(Product product) throws ClassNotFoundException, SomethingWentWrongException;

}
