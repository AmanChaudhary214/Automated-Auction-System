package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.dto.User;

public interface UserDAO {

	void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException;

	void logout();

	List<User> getAllUsersList() throws SomethingWentWrongException, NoRecordFoundException;

	void updateDetailsOfUser(String fName, String lName, String address, String mobNo) throws SomethingWentWrongException;

	void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException, NoRecordFoundException;

	void deleteUser() throws SomethingWentWrongException;

	static void addUser(User user) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String INSERT_QUERY = "INSERT INTO user (username, password, fName, lName, address, mobNo) VALUES (?, ?, ?, ?, ?, ?)";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);
			
			//stuff the data in the query
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getfName());
			ps.setString(4, user.getlName());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getMobNo());
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to login");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to login");
			}
		}
	}
}
