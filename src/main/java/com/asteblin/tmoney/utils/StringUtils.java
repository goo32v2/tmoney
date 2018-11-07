package com.asteblin.tmoney.utils;

/**
 * @author Alexandr Steblin
 */
public class StringUtils
{

  public static boolean isNotEmpty(final String str)
  {
    return !isEmpty(str);
  }

  public static boolean isEmpty(final String str)
  {
    return str != null && str.isEmpty();
  }
}
