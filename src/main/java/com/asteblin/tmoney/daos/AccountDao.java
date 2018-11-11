package com.asteblin.tmoney.daos;

import com.asteblin.tmoney.data.AccountData;

/**
 * @author Alexandr Steblin
 */
public interface AccountDao
{

  /**
   * Find account by id
   * @param id for search
   *
   * @return {@link AccountData} if find, or else null
   */
  AccountData findAccountById(String id);
}
