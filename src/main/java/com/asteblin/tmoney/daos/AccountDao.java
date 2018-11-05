package com.asteblin.tmoney.daos;

import com.asteblin.tmoney.data.AccountData;

/**
 * @author Alexandr Steblin
 */
public interface AccountDao
{

  AccountData getAccountById(String id);
}
