package com.asteblin.tmoney.data;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Alexandr Steblin
 */
public class TransactionData
{
  private String id;
  private Double amount;
  private String from;
  private String to;
  private TransactionStatus status;
  private Date creationDate;

  public TransactionData()
  {
    this.id = UUID.randomUUID().toString();
    this.creationDate = new Date();
  }

  public TransactionData from(String id)
  {
    this.from = id;
    return this;
  }

  public TransactionData to(String id)
  {
    this.to = id;
    return this;
  }

  public TransactionData withAmount(Double amount)
  {
    this.amount = amount;
    return this;
  }

  public TransactionData withStatus(TransactionStatus status)
  {
    this.status = status;
    return this;
  }

  public String getId()
  {
    return id;
  }

  public Date getCreationDate()
  {
    return creationDate;
  }

  public Double getAmount()
  {
    return amount;
  }

  public String getFrom()
  {
    return from;
  }

  public String getTo()
  {
    return to;
  }

  public TransactionStatus getStatus()
  {
    return status;
  }

  @Override
  public String toString()
  {
    return "TransactionData{" + "id='" + id + '\'' + ", amount=" + amount + ", from='" + from + '\'' + ", to='" + to + '\'' +
            ", status=" + status + ", creationDate=" + creationDate + '}';
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
    TransactionData that = (TransactionData) o;
    return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(from, that.from) &&
            Objects.equals(to, that.to) && status == that.status && Objects.equals(creationDate, that.creationDate);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, amount, from, to, status, creationDate);
  }
}
