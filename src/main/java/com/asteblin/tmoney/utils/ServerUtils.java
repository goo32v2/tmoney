package com.asteblin.tmoney.utils;

import java.util.HashMap;
import java.util.Map;

import com.asteblin.tmoney.api.AccountRoutes;
import com.asteblin.tmoney.api.TransactionRoutes;
import com.asteblin.tmoney.daos.AccountDao;
import com.asteblin.tmoney.daos.TransactionDao;
import com.asteblin.tmoney.daos.impl.DefaultInMemoryAccountDao;
import com.asteblin.tmoney.daos.impl.DefaultInMemoryTransactionDao;
import com.asteblin.tmoney.data.AccountData;
import com.asteblin.tmoney.services.TransactionService;
import com.asteblin.tmoney.services.impl.DefaultTransactionService;
import com.google.gson.Gson;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;

/**
 * @author Alexandr Steblin
 */
public class ServerUtils
{


  private static TransactionRoutes transactionRoutes;
  private static AccountRoutes accountRoutes;

  public static Undertow buildServer()
  {
    return Undertow.builder().addHttpListener(8080, "localhost").setHandler(initRoutes()).build();
  }

  public static void initDependencies()
  {
    Gson gson = new Gson();
    AccountDao accountDao = new DefaultInMemoryAccountDao(createTestAccounts());
    TransactionDao transactionDao = new DefaultInMemoryTransactionDao();
    TransactionService transactionService = new DefaultTransactionService(accountDao, transactionDao);
    transactionRoutes = new TransactionRoutes(transactionService, gson);
    accountRoutes = new AccountRoutes(accountDao, gson);
  }

  public static HttpHandler initRoutes()
  {
    return new RoutingHandler().post("/transfer", exchange -> transactionRoutes.transfer(exchange))
                               .get("/transactions", exchange -> transactionRoutes.getTransactions(exchange))
                               .get("/account", exchange -> accountRoutes.getAccount(exchange));
  }

  public static Map<String, AccountData> createTestAccounts()
  {
    Map<String, AccountData> inMemoryData = new HashMap<>();
    inMemoryData.put("00001", createAccount("00001", 0.0));
    inMemoryData.put("00002", createAccount("00002", 21050.0));
    inMemoryData.put("00003", createAccount("00003", 0.0));
    return inMemoryData;
  }

  private static AccountData createAccount(String id, Double amount)
  {
    AccountData account = new AccountData();
    account.setId(id);
    account.setAmount(amount);
    return account;
  }
}
