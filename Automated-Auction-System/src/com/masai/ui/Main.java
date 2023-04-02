package com.masai.ui;

import java.util.Scanner;

import com.masai.dao.LoggedINUser;
import com.masai.dao.UserDAO;
import com.masai.dto.User;
import com.masai.dto.UserImpl;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class Main {

	private static CategoryUI categoryUI;
	private static ProductUI productUI;
	private static UserUI userUI;
	private static TransactionUI transactionUI;
	
	
	static void displayAdminMenu() {
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.println("|         ***** ADMIN MENU *****         |");
		System.out.println("|                                        |");
		System.out.println("|   1. View all Users                    |");
		System.out.println("|   2. View All Products                 |");
		System.out.println("|   3. View Sold Products                |");
		System.out.println("|   4. View Products by Category         |");
		System.out.println("|   5. View all Transactions             |");
		System.out.println("|   6. View Transactions by Trans ID     |");
		System.out.println("|   7. View Transactions by Date Range   |");
		System.out.println("|                                        |");
		System.out.println("------------------------------------------");
		System.out.println();
		System.out.println("***** Enter your choice *****");
	}
	
	
	static void displayUserMenu() {
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("|         ***** USER MENU *****         |");
		System.out.println("|                                       |");
		System.out.println("|       1. Update User Details          |");
		System.out.println("|       2. Update User Password         |");
		System.out.println("|       3. Add Product                  |");
		System.out.println("|       4. Update Product               |");
		System.out.println("|       5. View All Products            |");
		System.out.println("|       6. Purchase a Product           |");
		System.out.println("|       7. View Order History           |");
		System.out.println("|       8. View All Transactions        |");
		System.out.println("|       9. Delete My Account            |");
		System.out.println("|       0. Logout                       |");
		System.out.println("|                                       |");
		System.out.println("-----------------------------------------");
		System.out.println();
		System.out.println("***** Enter your choice *****");
	}
	
	
	static void adminLogin(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException, ClassNotFoundException {
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		
		if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
			System.out.println();
			System.out.println("Welcome Admin");
			System.out.println();
			adminMenu(sc);
		}else {
			System.out.println("Invalid Username and Password");
		}
	}
	
	
	static void adminMenu(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException, ClassNotFoundException {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.println();
			System.out.print("Choice: ");
			choice = sc.nextInt();
			switch(choice) {
				case 0:
					System.out.println("GoodBye Admin");
					break;
				case 1:
					userUI.viewUsers();
					break;
				case 2:
					productUI.viewProducts();
					break;
				case 3:
					productUI.viewSoldProducts();
					break;
				case 4:
					productUI.viewProductsByCategory();
					break;
				case 5:
					transactionUI.viewAllTransations();
					break;
				case 6:
					transactionUI.viewTransByID();
					break;
				case 7:
					transactionUI.viewTransByDate();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(choice != 0);
	}


	private static void userLogin(Scanner sc) throws ClassNotFoundException, SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		if(!userUI.uLogin())
			return;

		//you are here means login is successful
		int choice = 0;
		do {
			displayUserMenu();
			System.out.print("Choice: ");
			choice = sc.nextInt();
			switch(choice) {
				case 1:
					userUI.updateUserDetails(sc);
					break;
				case 2:
					userUI.updatePassword();
					break;
				case 3:
					productUI.addProduct();
					break;
				case 4:
					productUI.updateProduct();
					break;
				case 5:
					productUI.viewProducts();
					break;
				case 6:
					productUI.purchaseProducts();
					break;
				case 7:
					productUI.viewOrders();
					break;
				case 8:
					transactionUI.viewAllTransations();
					break;
				case 9:
					userUI.deleteUser();
					try{
						Thread.sleep(2000);
					}catch(InterruptedException ex) {
						
					}
					//no break statement here i.e. after deletion of user account, logout will also take place
				case 0:
					userUI.uLogout();
					break;
				default:
					System.out.println("Invalid Selection, try again");
			}
		}while(LoggedINUser.loggedInUserId != 0);
	}


	private static void registerUser(Scanner sc) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		System.out.print("Enter userName ");
		String username = sc.next();
		System.out.print("Enter password ");
		String password = sc.next();
		System.out.print("Enter firstname ");
		String fName = sc.next();
		System.out.print("Enter lastname ");
		String lName = sc.next();
		System.out.print("Enter address ");
		String address = sc.next();
		System.out.print("Enter mobile no. ");
		String mobNo = sc.next();
		
		//create object for user with all details
		User user = new UserImpl(username, password, fName, lName, address, mobNo);
		
		try {
			UserDAO.addUser(user);;
			System.out.println("User registered successfully");
		}catch(SomethingWentWrongException ex) {
			System.out.println(ex);
		}
	}
	
	
	public static void main(String[] args) throws SomethingWentWrongException, NoRecordFoundException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		categoryUI = new CategoryUI(sc);
		productUI = new ProductUI(sc);
		userUI = new UserUI(sc);
		transactionUI = new TransactionUI(sc);
		
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("|                                                                             |");
		System.out.println("|         **********  WELCOME TO AUTOMATED AUCTION SYSTEM  **********         |");
		System.out.println("|                                                                             |");
		System.out.println("|  ===============  An open platform to buy or sell things.  ===============  |");
		System.out.println("|                                                                             |");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		
		System.out.println("                      -------------------------------------");
		System.out.println("                      |       ***** MAIN MENU *****       |");
		System.out.println("                      |                                   |");
		System.out.println("                      |     1. Login as Administrator     |");
		System.out.println("                      |     2. Login as User              |");
		System.out.println("                      |     3. Regiister as User          |");
		System.out.println("                      |     0. Exit                       |");
		System.out.println("                      |                                   |");
		System.out.println("                      -------------------------------------");
		System.out.println();
		
		System.out.println("                          ***** Enter your choice *****");
		int choice = sc.nextInt();
		
		do {
			switch(choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
				userLogin(sc);
				break;
			case 3:
				registerUser(sc);
				break;
			case 0:
				System.out.println("Thanks for Visiting.");
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		} while(choice!=0);
		
	}

}
