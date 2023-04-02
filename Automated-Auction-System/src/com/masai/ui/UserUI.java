package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.UserDAO;
import com.masai.dao.UserDAOImpl;
import com.masai.dto.User;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class UserUI {
	
	private UserDAO userDAO;
	private Scanner scanner;
	
	public UserUI(Scanner scanner) {
		this.userDAO = new UserDAOImpl();
		this.scanner = scanner;
	}	
	
	
	public boolean uLogin() {
		boolean loginSuccessful = false;

		System.out.print("Enter username ");
		String username = scanner.next();
		
		System.out.print("Enter password ");
		String password = scanner.next();
		
		try {
			userDAO.login(username, password);
			loginSuccessful = true;
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
		return loginSuccessful;
	}
	
	
	public void uLogout() {
		userDAO.logout();
	}
	
	
	public void viewUsers() throws SomethingWentWrongException, NoRecordFoundException {
		List<User> listUsers = userDAO.getAllUsersList();
		listUsers.forEach(System.out::println);
	}
	
	
	public void updateUserDetails(Scanner sc) {
		//code to take input new name
		System.out.print("Enter First Name: ");
		String fName = sc.next();
		
		System.out.print("Enter Last Name: ");
		String lName = sc.next();
		
		System.out.print("Enter address: ");
		String address = sc.next();
		
		System.out.print("Enter mobile no.: ");
		String mobNo = sc.next();
		
		try {
			userDAO.updateDetailsOfUser(fName, lName, address, mobNo);
			System.out.println("Details updated successfully");
		}catch(SomethingWentWrongException ex) {
			System.out.println(ex);
		}
	}
	
	
	public void updatePassword() {
		//code to take input new name
		System.out.print("Enter old password ");
		String oldPassword = scanner.next();
		
		System.out.print("Enter new password ");
		String newPassword = scanner.next();
		
		System.out.print("Re-type new password ");
		String newPasswordAgain = scanner.next();
		if(newPassword.equals(newPasswordAgain)) {
			try {
				userDAO.changePassword(oldPassword, newPassword);
				System.out.println("Password updated successfully");
			}catch(SomethingWentWrongException | NoRecordFoundException ex) {
				System.out.println(ex);
			}			
		}else {
			System.out.println("New password mismatched with re-typed new password");
		}
	}
	
	
	public void deleteUser() {
		try {
			userDAO.deleteUser();
			System.out.println("You are Logged out.\nDeleted your account");
		}catch(SomethingWentWrongException ex) {
			System.out.println(ex);
		}
	}
	
}
