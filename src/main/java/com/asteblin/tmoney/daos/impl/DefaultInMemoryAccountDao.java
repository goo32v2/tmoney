package com.asteblin.tmoney.daos.impl;


import java.util.Map;

import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.data.AccountData;

/**
 * @author Alexandr Steblin
 */
public class DefaultInMemoryAccountDao implements AccountDao
{

  private final Map<String, AccountData> inMemoryData;

  public DefaultInMemoryAccountDao(final Map<String, AccountData> accounts)
  {
    inMemoryData = accounts;
  }

  @Override
  public AccountData findAccountById(final String id)
  {
    return inMemoryData.get(id);
  }

}
