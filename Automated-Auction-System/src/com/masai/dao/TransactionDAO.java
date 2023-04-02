package com.masai.dao;

import java.util.List;

import com.masai.dto.Transaction;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface TransactionDAO {

	public List<Transaction> getAllTransactions() throws ClassNotFoundException, NoRecordFoundException, SomethingWentWrongException;


}
