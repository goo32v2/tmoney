package com.asteblin.tmoney.data;

import java.util.Objects;

/**
 * @author Alexandr Steblin
 */
public class AccountData
{

  private String id;
  private Double amount;

  public String getId()
  {
    return id;
  }

  public void setId(final String id)
  {
    this.id = id;
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
  public String toString()
  {
    return "AccountData{" + "id='" + id + '\'' + ", amount=" + amount + '}';
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
    AccountData that = (AccountData) o;
    return Objects.equals(id, that.id) && Objects.equals(amount, that.amount);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, amount);
  }
}
