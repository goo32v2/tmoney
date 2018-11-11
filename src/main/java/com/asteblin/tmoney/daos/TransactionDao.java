package com.asteblin.tmoney.daos;

import java.util.List;

import com.asteblin.tmoney.data.TransactionData;

/**
 * @author Alexandr Steblin
 */
public interface TransactionDao
{

  /**
   * Save given transaction
   * @param transaction to save
   */
  void saveTransaction(TransactionData transaction);

  /**
   * Find all transactions for given account id
   * @param accountId for search in 'to' and 'from'
   *
   * @return {@link List<TransactionData>} with all find transactions
   */
  List<TransactionData> findTransactionsByAccount(String accountId);
}
