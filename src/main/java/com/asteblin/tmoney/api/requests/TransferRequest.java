package com.asteblin.tmoney.api.requests;

import java.util.Objects;

/**
 * @author Alexandr Steblin
 */
public class TransferRequest
{
  private String to;
  private String from;
  private Double amount;

  public TransferRequest()
  {
  }

  public TransferRequest(final String to, final String from, final Double amount)
  {
    this.to = to;
    this.from = from;
    this.amount = amount;
  }

  public String getTo()
  {
    return to;
  }

  public void setTo(final String to)
  {
    this.to = to;
  }

  public String getFrom()
  {
    return from;
  }

  public void setFrom(final String from)
  {
    this.from = from;
  }

  public Double getAmount()
  {
    return amount;
  }

  public void setAmount(final Double amount)
  {
    this.amount = amount;
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
    TransferRequest that = (TransferRequest) o;
    return Objects.equals(to, that.to) && Objects.equals(from, that.from) && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(to, from, amount);
  }

  @Override
  public String toString()
  {
    return "TransferRequest{" + "to='" + to + '\'' + ", from='" + from + '\'' + ", amount=" + amount + '}';
  }
}
