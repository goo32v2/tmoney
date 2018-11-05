package com.asteblin.tmoney.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.asteblin.tmoney.BaseApiTest;
import com.asteblin.tmoney.api.requests.TransferRequest;
import com.asteblin.tmoney.data.TransactionData;
import com.asteblin.tmoney.data.TransactionStatus;

import static org.junit.Assert.*;

/**
 * @author Alexandr Steblin
 */
public class TransactionRoutesTest extends BaseApiTest
{

  @Test
  public void transfer() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 1500.0);
    TransactionData transactionData = makePostRequest("/transfer", request, TransactionData.class);
    assertNotNull(transactionData);
    assertEquals(TransactionStatus.SUCCESS, transactionData.getStatus());
  }

  @Test
  public void transferNotEnoughAmount() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 99999.0);
    TransactionData transactionData = makePostRequest("/transfer", request, TransactionData.class);
    assertNotNull(transactionData);
    assertEquals(TransactionStatus.NOT_ENOUGH_AMOUNT, transactionData.getStatus());
  }

  @Test
  public void transferAccountNotFound() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "", 10.0);
    TransactionData transactionData = makePostRequest("/transfer", request, TransactionData.class);
    assertNotNull(transactionData);
    assertEquals(TransactionStatus.ACCOUNT_NOT_FOUND, transactionData.getStatus());
  }

  @Test
  public void getTransactions() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, TransactionData.class);
    request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, TransactionData.class);

    ArrayList arrayList = makeGetRequest("/transactions?accountId=00001", ArrayList.class);

    assertNotNull(arrayList);
    assertEquals(2, arrayList.size());
  }

  @Test
  public void getTransactionsForAccountWithZeroTransactions() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, TransactionData.class);
    request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, TransactionData.class);

    ArrayList arrayList = makeGetRequest("/transactions?accountId=00003", ArrayList.class);

    assertNotNull(arrayList);
    assertEquals(0, arrayList.size());
  }

}