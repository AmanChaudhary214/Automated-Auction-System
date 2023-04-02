package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.dto.ProductImpl;
import com.masai.dto.Transaction;
import com.masai.dto.TransactionImpl;
import com.masai.dto.UserImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class TransactionDAOImpl implements TransactionDAO{

	private List<Transaction> getOrderListFromResultSet(ResultSet resultSet) throws SQLException{
		List<Transaction> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of Employee
			Transaction orders = new TransactionImpl();
			orders.setProduct(new ProductImpl(null , resultSet.getString("pro_name"), null, null, null));
			orders.setUser(new UserImpl(null, resultSet.getString("name"), null, null, null));
			orders.setQuantity(resultSet.getInt("quantity"));
			orders.setPurchaseDate(resultSet.getDate("purchase_date").toLocalDate());
			list.add(orders);
		}
		return list;
	}
	
	
	@Override
	public List<Transaction> getAllTransactions() throws ClassNotFoundException, NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<Transaction> list = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			
			//prepare the query
			String SELECT_QUERY = "SELECT P.pro_name, U.name, quantity, purchase_date "
					+ "FROM orders O INNER JOIN Product P ON O.pro_id = P.pro_id INNER JOIN user U "
					+ "ON U.user_id = O.user_id";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtility.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No Transaction Found");
			}
			
			list = getOrderListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Something went wrong");
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Something went wrong");
			}
		}
		return list;
	}

}
