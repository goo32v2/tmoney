package com.asteblin.tmoney.services;

import java.util.List;

import com.asteblin.tmoney.data.TransactionData;

/**
 * @author Alexandr Steblin
 */
public interface TransactionService
{

  TransactionData transfer(String to, String from, Double amount);

  List<TransactionData> getTransactionsByAccount(String accountId);
}
