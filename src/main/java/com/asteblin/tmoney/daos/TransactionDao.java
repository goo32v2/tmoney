package com.asteblin.tmoney.daos;

import java.util.List;

import com.asteblin.tmoney.data.TransactionData;

/**
 * @author Alexandr Steblin
 */
public interface TransactionDao
{

  void saveTransaction(TransactionData transaction);

  List<TransactionData> getTransactionsByAccount(String accountId);
}
