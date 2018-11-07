package com.asteblin.tmoney.api;

import java.util.Deque;

import com.asteblin.tmoney.data.DataWrapper;
import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * @author Alexandr Steblin
 */
public abstract class AbstractRoutes
{

  private final Gson gson;

  protected AbstractRoutes(final Gson gson)
  {
    this.gson = gson;
  }

  public void sendJson(final HttpServerExchange httpServerExchange, final Object data)
  {
    httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
    httpServerExchange.getResponseSender().send(gson.toJson(data));
  }

  public String getParam(final HttpServerExchange httpServerExchange, final String paramName)
  {
    Deque<String> paramValues = httpServerExchange.getQueryParameters().get(paramName);
    return paramValues != null ? paramValues.getFirst() : "";
  }

  public <T> T deserializePostData(final String data, Class<T> clazz)
  {
    return gson.fromJson(data, clazz);
  }

  public <T> DataWrapper<T> createResponse(Integer errorCode, String errorMsg)
  {
    DataWrapper<T> res = new DataWrapper<>();
    res.setErrorCode(errorCode);
    res.setErrorMsg(errorMsg);
    res.setStatus(false);
    return res;
  }

  public <T> DataWrapper<T> createResponse(T data)
  {
    DataWrapper<T> res = new DataWrapper<>();
    res.setData(data);
    res.setStatus(true);
    return res;
  }
}
