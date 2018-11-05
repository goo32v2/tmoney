package com.asteblin.tmoney.daos.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.asteblin.tmoney.daos.TransactionDao;
import com.asteblin.tmoney.data.TransactionData;

/**
 * @author Alexandr Steblin
 */
public class DefaultInMemoryTransactionDao implements TransactionDao
{

  private final List<TransactionData> inMemoryData;

  public DefaultInMemoryTransactionDao()
  {
    inMemoryData = new ArrayList<>();
  }

  @Override
  public void saveTransaction(final TransactionData transaction)
  {
    inMemoryData.add(transaction);
  }

  @Override
  public List<TransactionData> getTransactionsByAccount(final String accountId)
  {
    return inMemoryData.stream()
                       .filter(transaction -> accountId.equals(transaction.getFrom()) || accountId.equals(transaction.getTo()))
                       .collect(Collectors.toList());
  }
}
