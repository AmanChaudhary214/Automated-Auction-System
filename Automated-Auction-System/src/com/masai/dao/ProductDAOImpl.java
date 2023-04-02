package com.masai.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.Product;
import com.masai.dto.ProductImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class ProductDAOImpl implements ProductDAO{
	
	
	public List<Product> getAllProducts() throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		List<Product> list = new ArrayList<>();
		try {
			conn = DBUtility.getConnectiontoDatabase();
			String query = "SELECT prductId, productName, productDesc, price, quantity, categoryId, mfgDate, GST, sold_status FROM product";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(DBUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No product found");
			}
			while(rs.next()) {
				list.add(new ProductImpl(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getDouble(8), rs.getString(9)));
			}
			
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to update the record now, try again later");
		}finally {
			try {
				DBUtility.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
		return list;
	}
	

	@Override	
	public List<Product> getProductsByCategoryId(String categoryId) throws SomethingWentWrongException, NoRecordFoundException {
		Connection conn = null;
		List<Product> list = new ArrayList<>();
		try {
			conn = DBUtility.getConnectiontoDatabase();
			String query = "SELECT * FROM product WHERE categoryId = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, categoryId);
			
			ResultSet rs = ps.executeQuery();
			if(DBUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No product found");
			}
			while(rs.next()) {
				list.add(new ProductImpl(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getDouble(8), rs.getString(9)));
				}
			
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to update the record now, try again later");
		}finally {
			try {
				DBUtility.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
		return list;
	}
	
	
	@Override
	public void addProduct(Product product) throws SomethingWentWrongException {
		Connection conn = null;
		try {
			conn = DBUtility.getConnectiontoDatabase();
			String query = "INSERT INTO product (prductId, productName, productDesc, price, quantity, categoryId, mfgDate, GST, sold_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, product.getPrductId());
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getProductDesc());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getQuantity());
			ps.setString(6, product.getCategoryId());
			ps.setDate(7, Date.valueOf(product.getMfgDate()));
			ps.setDouble(8, product.getGST());
			ps.setString(9, product.getSold_status());
			
			ps.executeUpdate();
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to insert the record now, try again later");
		}finally {
			try {
				DBUtility.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
	}
	

	@Override	
	public void updateProduct(Product product) throws SomethingWentWrongException {
		Connection conn = null;
		try {
			conn = DBUtility.getConnectiontoDatabase();
			String query = "UPDATE product SET productName=?, productDesc=?, price=?, quantity=?, categoryId=?, mfgDate=?, GST=?, sold_status=? WHERE prductId = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, product.getProductName());
			ps.setString(2, product.getProductDesc());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getQuantity());
			ps.setString(5, product.getCategoryId());
			ps.setDate(6, Date.valueOf(product.getMfgDate()));
			ps.setDouble(7, product.getGST());
			ps.setString(8, product.getSold_status());
			ps.setString(9, product.getPrductId());
			
			ps.executeUpdate();
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to insert the record now, try again later");
		}finally {
			try {
				DBUtility.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
	}


	@Override
	public List<Product> getSoldProducts() throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection conn = null;
		List<Product> list = new ArrayList<>();
		try {
			conn = DBUtility.getConnectiontoDatabase();
			String query = "SELECT * FROM product WHERE sold_Status = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, "sold");
			
			ResultSet rs = ps.executeQuery();
			if(DBUtility.isResultSetEmpty(rs)) {
				throw new NoRecordFoundException("No product found");
			}
			while(rs.next()) {
				list.add(new ProductImpl(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7).toLocalDate(), rs.getDouble(8), rs.getString(9)));
				}
			
		}catch(ClassNotFoundException | SQLException ex) {
			throw new SomethingWentWrongException("Unable to update the record now, try again later");
		}finally {
			try {
				DBUtility.closeConnection(conn);					
			}catch(SQLException ex) {
				
			}
		}
		return list;
	}

}
