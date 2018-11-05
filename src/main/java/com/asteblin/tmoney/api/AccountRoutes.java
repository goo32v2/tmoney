package com.asteblin.tmoney.api;

import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.data.AccountData;
import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;

/**
 * @author Alexandr Steblin
 */
public class AccountRoutes extends AbstractRoutes
{

  private final AccountDao accountDao;

  public AccountRoutes(final AccountDao accountDao, final Gson gson)
  {
    super(gson);
    this.accountDao = accountDao;
  }

  public void getAccount(final HttpServerExchange httpServerExchange)
  {
    String id = getParam(httpServerExchange, "accountId");
    AccountData accountById = accountDao.getAccountById(id);
    sendJson(httpServerExchange, accountById);
  }
}
