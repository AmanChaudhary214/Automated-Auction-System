package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.dao.TransactionDAO;
import com.masai.dao.TransactionDAOImpl;
import com.masai.dto.Transaction;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class TransactionUI {
	
	private TransactionDAO transactionDAO;
	private Scanner scanner;

	public TransactionUI(Scanner scanner) {
		// TODO Auto-generated constructor stub
		transactionDAO = new TransactionDAOImpl();
		this.scanner = scanner;
	}

	
	public void viewAllTransations() {
		// TODO Auto-generated method stub
		try {
			List<Transaction> transList  = TransactionDAO.getAllTransactions();
			transList.forEach(System.out::println);
		}catch(SomethingWentWrongException | NoRecordFoundException ex) {
			System.out.println(ex);
		}
	}

	
	public void viewTransByID() {
		// TODO Auto-generated method stub
		
	}

	
	public void viewTransByDate() {
		// TODO Auto-generated method stub
		
	}

}
