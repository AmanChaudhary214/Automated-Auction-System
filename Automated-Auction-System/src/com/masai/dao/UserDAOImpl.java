package com.masai.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.dao.DBUtility;
import com.masai.dao.LoggedINUser;
import com.masai.dto.User;
import com.masai.dto.UserImpl;
import com.masai.exception.SomethingWentWrongException;


public class UserDAOImpl implements UserDAO {
	

	@Override
	public void login(String username, String password) throws SomethingWentWrongException, NoRecordFoundException{
		Connection conn = null;
		try {
			//connect to database
			conn = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String LOGIN_QUERY = "SELECT userId FROM user WHERE username = ? AND password = ?";
			
			//get the prepared statement object
			PreparedStatement ps = conn.prepareStatement(LOGIN_QUERY);
			
			//stuff the data in the query
			ps.setString(1, username);
			ps.setString(2, password);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			if(DBUtility.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("Invalid Username and Password");
			}
			
			//you are here means username and password combination is correct
			resultSet.next();
			LoggedINUser.loggedInUserId = resultSet.getInt("userId");
		}catch(SQLException ex) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to login");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(conn);				
			}catch(SQLException eX) {
				throw new SomethingWentWrongException("Unable to login");
			}
		}
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		LoggedINUser.loggedInUserId = 0;
	}
	
	
	private List<User> getUserListFromResultSet(ResultSet resultSet) throws SQLException{
		List<User> list = new ArrayList<>();
		while(resultSet.next()) {
			//Create an object of User
			User user = new UserImpl();
			user.setUsername(resultSet.getString("username"));
			user.setfName(resultSet.getString("fName"));
			user.setlName(resultSet.getString("lName"));
			user.setAddress(resultSet.getString("address"));
			user.setMobNo(resultSet.getString("mobNo"));
			list.add(user);
		}
		return list;
	}

	@Override
	public List<User> getAllUsersList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		Connection connection = null;
		List<User> list = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String SELECT_QUERY = "SELECT * FROM user";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			
			//check if result set is empty
			if(DBUtility.isResultSetEmpty(resultSet)) {
				throw new NoRecordFoundException("No user Found");
			}
			
			list = getUserListFromResultSet(resultSet);
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to fetch list");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to fetch list");
			}
		}
		return list;
	}

	@Override
	public void updateDetailsOfUser(String fName, String lName, String address, String mobNo) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			//connect to database
			conn = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET fName = ?, lName = ?, address = ?, mobNo = ? WHERE userId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = conn.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, address);
			ps.setString(4, mobNo);
			ps.setInt(5, LoggedINUser.loggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to update");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(conn);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to update");
			}
		}
	}


	private boolean isOldPasswordCorrect(String oldPassword) throws SomethingWentWrongException {
		Connection connection = null;
		boolean isPasswordValid = false;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String CHECK_PASSWORD_QUERY = "SELECT count(*) as count FROM user WHERE password = ? AND userId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(CHECK_PASSWORD_QUERY);
			
			//stuff the data in the query
			ps.setString(1, oldPassword);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			
			//execute query
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			
			isPasswordValid = resultSet.getInt("count") == 1;
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to change password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to change password");
			}
		}
		return isPasswordValid;
	}
	
	
	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException, NoRecordFoundException{
		if(!isOldPasswordCorrect(oldPassword)) {
			throw new NoRecordFoundException("Invalid old password!");
		}
		
		//you are here means old password matched
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			//prepare the query
			String UPDATE_QUERY = "UPDATE user SET password = ? WHERE userId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
			
			//stuff the data in the query
			ps.setString(1, newPassword);
			ps.setInt(2, LoggedINUser.loggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			sqlEx.printStackTrace();
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to change password");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to change password");
			}
		}
	}

	
	@Override
	public void deleteUser() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			//connect to database
			connection = DBUtility.getConnectiontoDatabase();
			
			//prepare the query
			String DELETE_QUERY = "DELETE FROM user WHERE useId = ?";
			
			//get the prepared statement object
			PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
			
			//stuff the data in the query
			ps.setInt(1, LoggedINUser.loggedInUserId);
			
			//execute query
			ps.executeUpdate();
		}catch(SQLException sqlEx) {
			//code to log the error in the file
			throw new SomethingWentWrongException("Unable to delete");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//close the exception
				DBUtility.closeConnection(connection);				
			}catch(SQLException sqlEX) {
				throw new SomethingWentWrongException("Unable to delete");
			}
		}
	}

}
