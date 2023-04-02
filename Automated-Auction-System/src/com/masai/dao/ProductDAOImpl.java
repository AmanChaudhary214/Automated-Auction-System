package com.masai.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Product;
import com.masai.dto.CategoryImpl;
import com.masai.dto.ProductImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class ProductDAOImpl implements ProductDAO{
	
	private List<Product> getProductListFromResultSet(ResultSet resultSet) throws SQLException{
		List<Product> list = new ArrayList<>();
		while(resultSet.next()) {
			Product product = new ProductImpl();
			product.setPrductId(resultSet.getString("prductId"));
			product.setProductName(resultSet.getString("productName"));
			product.setProductDesc(resultSet.getString("productDesc"));
			product.setPrice(resultSet.getInt("price"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setCategoryId(resultSet.getString("categoryId"));
			product.setMfgDate(resultSet.getDate("mfgDate").toLocalDate());
			product.setGST(resultSet.getDouble("GST"));
			product.setSold_status(resultSet.getString("sold_status"));
			
			if(resultSet.getInt("cat_id") == 0) {
				//you are here means the product does not belong to any category
				product.setCategory(null);
			}else {
				//you are here means the product belongs to a category
				product.setCategory(new CategoryImpl(resultSet.getInt("cat_id"), resultSet.getString("cat_name")));
			}

			list.add(product);
		}
		return list;
	}

	@Override
	public List<Product> getAllProducts() throws SomethingWentWrongException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Product> list = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT prductId, productName, productDesc, price, quantity, categoryId, mfgDate, GST, sold_status FROM product";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtility.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No product Found");
			}
			
			list = getProductListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("No product Found");
		} catch (NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("No product Found");
			}
		}
		return list;
	}

	@Override
	public List<Product> getProductsByCategoryId(String categoryId) throws NoRecordFoundException, ClassNotFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Product> list = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM product WHERE categoryId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, categoryId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtility.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No product Found in this category");
			}
			
			list = getProductListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("No product Found in this category");
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("No product Found in this category");
			}
		}
		return list;
	}

	@Override
	public void updateProduct(Product product) throws ClassNotFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE product SET productName=?, productDesc=?, price=?, quantity=?, categoryId=?, mfgDate=?, GST=?, sold_status=? FROM product WHERE prductId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDesc());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getQuantity());
			ps.setString(5, product.getCategoryId());
			ps.setDate(6, Date.valueOf(product.getMfgDate()));
			ps.setDouble(7, product.getGST());
			ps.setString(8, product.getSold_status());
			ps.setString(9, product.getPrductId());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to update");
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to update");
			}
		}
	}

	@Override
	public void addProduct(Product product) throws ClassNotFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO product (productName, productDesc, price, quantity, categoryId, mfgDate, GST, sold_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDesc());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getQuantity());
			ps.setString(5, product.getCategoryId());
			ps.setDate(6, Date.valueOf(product.getMfgDate()));
			ps.setDouble(7, product.getGST());
			ps.setString(8, product.getSold_status());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to add product");
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to add product");
			}
		}	
	}

}
