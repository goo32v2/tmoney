package com.asteblin.tmoney.api;

import java.io.IOException;

import org.junit.Test;

import com.asteblin.tmoney.BaseApiTest;
import com.asteblin.tmoney.data.DataWrapper;
import com.asteblin.tmoney.data.AccountData;
import com.google.gson.reflect.TypeToken;

import static org.junit.Assert.*;

/**
 * @author Alexandr Steblin
 */
public class AccountRoutesTest extends BaseApiTest
{

  @Test
  public void getAccount() throws IOException
  {
    DataWrapper<AccountData> response = makeGetRequest("/account?accountId=00001", new TypeToken<DataWrapper<AccountData>>(){});
    assertNotNull(response);
    assertTrue(response.isStatus());
    assertNotNull(response.getData());
    assertEquals("00001", response.getData().getId());
  }

  @Test
  public void getAccountForNotExistedAccount() throws IOException
  {
    DataWrapper<AccountData> response = makeGetRequest("/account?accountId=", new TypeToken<DataWrapper<AccountData>>(){});
    assertNotNull(response);
    assertFalse(response.isStatus());
    assertNull(response.getData());
  }

}