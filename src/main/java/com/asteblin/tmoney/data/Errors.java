package com.asteblin.tmoney.data;

/**
 * @author Alexandr Steblin
 */
public enum Errors
{
  UNEXPECTED_ERROR(000), ILLEGAL_ARGUMENT(001);

  private int code;

  Errors(final int code)
  {
    this.code = code;
  }

  public int getCode()
  {
    return code;
  }

}

