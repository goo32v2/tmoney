package com.asteblin.tmoney.api;

import java.io.IOException;

import org.junit.Test;

import com.asteblin.tmoney.BaseApiTest;
import com.asteblin.tmoney.data.AccountData;

import okhttp3.Request;
import okhttp3.Response;

import static org.junit.Assert.*;

/**
 * @author Alexandr Steblin
 */
public class AccountRoutesTest extends BaseApiTest
{

  @Test
  public void getAccount() throws IOException
  {
    AccountData accountData = makeGetRequest("/account?accountId=00001", AccountData.class);
    assertNotNull(accountData);
    assertEquals("00001", accountData.getId());
  }

  @Test
  public void getAccountForNotExistedAccount() throws IOException
  {
    AccountData accountData = makeGetRequest("/account?accountId=", AccountData.class);
    assertNull(accountData);
  }

}