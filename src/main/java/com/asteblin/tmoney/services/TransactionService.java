package com.asteblin.tmoney.services;

import java.util.List;

import com.asteblin.tmoney.data.TransactionData;

/**
 * @author Alexandr Steblin
 */
public interface TransactionService
{

  /**
   * Transfer given amount from one account to another
   * @param to account
   * @param from account
   * @param amount to transfer
   *
   * @return {@link TransactionData} with operation status
   */
  TransactionData transfer(String to, String from, Double amount);

  /**
   * Get all transactions for given account id
   * @param accountId for search
   *
   * @return {@link List<TransactionData>} with all find transactions
   */
  List<TransactionData> getTransactionsByAccount(String accountId);
}
