package com.masai.ui;

import java.util.Scanner;

public class MainUI {

	private static void adminLogin(Scanner sc) {
		// TODO Auto-generated method stub
		
	}

	private static void signInUser(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	
	private static void registerUser(Scanner sc) {
		// TODO Auto-generated method stub
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==========**********===== WELCOME TO AUTOMATED AUCTION SYSTEM =====**********==========");
		System.out.println();
		System.out.println("======================= An open platform to buy or sell things. =======================");
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("------ 1. Login as Administrator ------");
		System.out.println("---------- 2. Login as User -----------");
		System.out.println("-------- 3. Regiister as User ---------");
		System.out.println("--------------- 0. Exit ---------------");
		System.out.println();
		
		System.out.println("***** Enter your choice *****");
		int choice = sc.nextInt();
		
		do {
			switch(choice) {
			case 1:
				adminLogin(sc);
				break;
			case 2:
				registerUser(sc);
				break;
			case 3:
				signInUser(sc);
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
