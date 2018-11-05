package com.asteblin.tmoney.daos.impl;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.data.AccountData;

/**
 * @author Alexandr Steblin
 */
public class DefaultInMemoryAccountDao implements AccountDao
{

  private final ConcurrentMap<String, AccountData> inMemoryData;

  public DefaultInMemoryAccountDao()
  {
    inMemoryData = new ConcurrentHashMap<>();
    inMemoryData.put("00001", createAccount("00001", 0.0));
    inMemoryData.put("00002", createAccount("00002", 21050.0));
    inMemoryData.put("00003", createAccount("00003", 0.0));
  }

  @Override
  public AccountData getAccountById(final String id)
  {
    return inMemoryData.get(id);
  }

  private AccountData createAccount(String id, Double amount)
  {
    AccountData account = new AccountData();
    account.setId(id);
    account.setAmount(amount);
    return account;
  }
}
