package com.asteblin.tmoney.data;

import java.util.Objects;

/**
 * @author Alexandr Steblin
 */
public class DataWrapper<T>
{
  private boolean status;
  private T data;
  private String errorMsg;
  private int errorCode;

  public boolean isStatus()
  {
    return status;
  }

  public void setStatus(final boolean status)
  {
    this.status = status;
  }

  public T getData()
  {
    return data;
  }

  public void setData(final T data)
  {
    this.data = data;
  }

  public String getErrorMsg()
  {
    return errorMsg;
  }

  public void setErrorMsg(final String errorMsg)
  {
    this.errorMsg = errorMsg;
  }

  public int getErrorCode()
  {
    return errorCode;
  }

  public void setErrorCode(final int errorCode)
  {
    this.errorCode = errorCode;
  }

  @Override
  public boolean equals(final Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    DataWrapper that = (DataWrapper) o;
    return status == that.status && Objects.equals(data, that.data) && Objects.equals(errorMsg, that.errorMsg) &&
            Objects.equals(errorCode, that.errorCode);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(status, data, errorMsg, errorCode);
  }

  @Override
  public String toString()
  {
    return "ResponseWrapper{" + "status=" + status + ", data=" + data + ", errorMsg='" + errorMsg + '\'' + ", errorCode='" +
            errorCode + '\'' + '}';
  }
}
