package com.asteblin.tmoney.api;

import java.util.List;

import com.asteblin.tmoney.api.requests.TransferRequest;
import com.asteblin.tmoney.data.TransactionData;
import com.asteblin.tmoney.services.TransactionService;
import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;

/**
 * @author Alexandr Steblin
 */
public class TransactionRoutes extends AbstractRoutes
{

  private final TransactionService transactionService;

  public TransactionRoutes(final TransactionService transactionService, final Gson gson)
  {
    super(gson);
    this.transactionService = transactionService;
  }

  public void transfer(final HttpServerExchange httpServerExchange)
  {
    httpServerExchange.getRequestReceiver().receiveFullString((exchange, message) -> {
      TransferRequest transferRequest = deserializePostData(message, TransferRequest.class);
      TransactionData transfer = transactionService.transfer(transferRequest.getTo(),
                                                             transferRequest.getFrom(),
                                                             transferRequest.getAmount()
      );
      sendJson(exchange, transfer);
    });

  }

  public void getTransactions(final HttpServerExchange httpServerExchange)
  {
    String accountId = getParam(httpServerExchange, "accountId");
    List<TransactionData> transactionsByAccount = transactionService.getTransactionsByAccount(accountId);
    sendJson(httpServerExchange, transactionsByAccount);
  }
}
