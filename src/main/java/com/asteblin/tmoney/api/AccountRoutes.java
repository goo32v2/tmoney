package com.asteblin.tmoney.api;

import com.asteblin.tmoney.data.Errors;
import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.data.AccountData;
import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;

import static com.asteblin.tmoney.utils.StringUtils.isNotEmpty;

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
    String accountId = getParam(httpServerExchange, "accountId");
    if (isNotEmpty(accountId))
    {
      AccountData accountById = accountDao.findAccountById(accountId);
      sendJson(httpServerExchange, createResponse(accountById));
    }
    else
    {
      sendJson(httpServerExchange, createResponse(Errors.ILLEGAL_ARGUMENT.getCode(),
              "Attribute 'accountId' must not be null"));
    }
  }
}
