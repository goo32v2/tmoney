package com.asteblin.tmoney.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.asteblin.tmoney.BaseApiTest;
import com.asteblin.tmoney.api.requests.TransferRequest;
import com.asteblin.tmoney.data.DataWrapper;
import com.asteblin.tmoney.data.TransactionData;
import com.asteblin.tmoney.data.TransactionStatus;
import com.google.gson.reflect.TypeToken;

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
    DataWrapper<TransactionData> response = makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    assertNotNull(response);
    assertTrue(response.isStatus());
    assertNotNull(response.getData());
    assertEquals(TransactionStatus.SUCCESS, response.getData().getStatus());
  }

  @Test
  public void transferNotEnoughAmount() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 99999.0);
    DataWrapper<TransactionData> response = makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    assertNotNull(response);
    assertTrue(response.isStatus());
    assertNotNull(response.getData());
    assertEquals(TransactionStatus.NOT_ENOUGH_AMOUNT, response.getData().getStatus());
  }

  @Test
  public void transferAccountNotFound() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "", 10.0);
    DataWrapper<TransactionData> response = makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    assertNotNull(response);
    assertFalse(response.isStatus());
    assertNull(response.getData());
  }

  @Test
  public void transferAccountsNotGiven() throws IOException
  {
    TransferRequest request = new TransferRequest("", "", 10.0);
    DataWrapper<TransactionData> response = makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    assertNotNull(response);
    assertFalse(response.isStatus());
    assertNull(response.getData());
  }

  @Test
  public void getTransactions() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});

    DataWrapper<List<TransactionData>> response = makeGetRequest("/transactions?accountId=00001", new TypeToken<DataWrapper<ArrayList<TransactionData>>>() {});
    assertNotNull(response);
    assertTrue(response.isStatus());
    assertNotNull(response.getData());
    assertEquals(2, response.getData().size());
  }

  @Test
  public void getTransactionsForAccountWithZeroTransactions() throws IOException
  {
    TransferRequest request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});
    request = new TransferRequest("00001", "00002", 10.0);
    makePostRequest("/transfer", request, new TypeToken<DataWrapper<TransactionData>>(){});

    DataWrapper<List<TransactionData>> response = makeGetRequest("/transactions?accountId=00003", new TypeToken<DataWrapper<ArrayList<TransactionData>>>() {});
    assertNotNull(response);
    assertTrue(response.isStatus());
    assertNotNull(response.getData());
    assertEquals(0, response.getData().size());
  }

}