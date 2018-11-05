package com.asteblin.tmoney;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

import com.google.gson.Gson;

import io.undertow.Undertow;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.asteblin.tmoney.utils.ServerUtils.buildServer;
import static com.asteblin.tmoney.utils.ServerUtils.initDependencies;

/**
 * @author Alexandr Steblin
 */
public abstract class BaseApiTest
{
  private OkHttpClient client = new OkHttpClient();
  private Gson gson = new Gson();
  private Undertow server;

  protected String BASE_URL = "http://localhost:8080";

  @Before
  public void setUp() throws Exception
  {
    initDependencies();
    server = buildServer();
    server.start();
  }

  @After
  public void tearDown() throws Exception
  {
    server.stop();
  }

  public <T> T makeGetRequest(final String url, Class<T> clazz) throws IOException
  {
    Request request = createRequest().url(BASE_URL + url).build();
    return makeRequest(clazz, request);
  }

  public <T> T makePostRequest(final String url, final Object requestData, Class<T> clazz) throws IOException
  {
    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(JSON, gson.toJson(requestData));
    Request request = createRequest().url(BASE_URL + url).post(body).build();

    return makeRequest(clazz, request);
  }

  private <T> T makeRequest(final Class<T> clazz, final Request request) throws IOException
  {
    Response response = client.newCall(request).execute();
    T data = null;
    if (response.body() != null)
    {
      data = fromJson(response.body().string(), clazz);
    }
    return data;
  }

  private Request.Builder createRequest()
  {
    return new Request.Builder()
            .addHeader("Content-Type", "application/json");
  }

  private <T> T fromJson(final String data, Class<T> clazz)
  {
    return gson.fromJson(data, clazz);
  }

}
