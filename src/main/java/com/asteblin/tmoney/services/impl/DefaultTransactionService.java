package com.asteblin.tmoney.services.impl;

import java.util.List;

import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.daos.TransactionDao;
import com.asteblin.tmoney.data.AccountData;
import com.asteblin.tmoney.data.TransactionData;
import com.asteblin.tmoney.data.TransactionStatus;
import com.asteblin.tmoney.services.TransactionService;

/**
 * @author Alexandr Steblin
 */
public class DefaultTransactionService implements TransactionService
{

  private final AccountDao     accountDao;
  private final TransactionDao transactionDao;

  public DefaultTransactionService(AccountDao accountDao, final TransactionDao transactionDao)
  {
    this.accountDao = accountDao;
    this.transactionDao = transactionDao;
  }

  @Override
  public TransactionData transfer(final String to, final String from, final Double amount)
  {
    TransactionData transaction = new TransactionData();
    transaction.from(from).to(to).withAmount(amount);

    AccountData toAccount = accountDao.findAccountById(to);
    AccountData fromAccount = accountDao.findAccountById(from);
    if (toAccount != null && fromAccount != null)
    {
      if (fromAccount.getAmount() != null && fromAccount.getAmount() >= amount)
      {
        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);
        transaction.withStatus(TransactionStatus.SUCCESS);
      }
      else
      {
        transaction.withStatus(TransactionStatus.NOT_ENOUGH_AMOUNT);
      }
      transactionDao.saveTransaction(transaction);
    }
    return transaction;
  }

  @Override
  public List<TransactionData> getTransactionsByAccount(final String accountId)
  {
    return transactionDao.findTransactionsByAccount(accountId);
  }

}
