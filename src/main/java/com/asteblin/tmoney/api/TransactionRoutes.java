package com.asteblin.tmoney.api;

import java.util.List;

import com.asteblin.tmoney.api.requests.TransferRequest;
import com.asteblin.tmoney.data.Errors;
import com.asteblin.tmoney.data.TransactionData;
import com.asteblin.tmoney.services.TransactionService;
import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;

import static com.asteblin.tmoney.utils.StringUtils.isNotEmpty;

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

      if (isNotEmpty(transferRequest.getTo()) && isNotEmpty(transferRequest.getFrom()) && transferRequest.getAmount() != null)
      {
        TransactionData transfer = transactionService.transfer(transferRequest.getTo(),
                                                               transferRequest.getFrom(),
                                                               transferRequest.getAmount()
        );
        sendJson(exchange, createResponse(transfer));
      }
      else
      {
        sendJson(exchange, createResponse(Errors.ILLEGAL_ARGUMENT.getCode(),
                                               "Attributes 'to', 'from' and 'amount' must not be null"));
      }
    });

  }

  public void getTransactions(final HttpServerExchange httpServerExchange)
  {
    String accountId = getParam(httpServerExchange, "accountId");
    if (accountId != null)
    {
      List<TransactionData> transactionsByAccount = transactionService.getTransactionsByAccount(accountId);
      sendJson(httpServerExchange, createResponse(transactionsByAccount));
    }
    else
    {
      sendJson(httpServerExchange, createResponse(Errors.ILLEGAL_ARGUMENT.getCode(),
                                             "Attribute 'accountId' must not be null"));
    }
  }
}
