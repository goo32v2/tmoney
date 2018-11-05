package com.asteblin.tmoney;

import io.undertow.Undertow;

import static com.asteblin.tmoney.utils.ServerUtils.*;

/**
 * @author Alexandr Steblin
 */
public class Main
{
  public static void main(String[] args)
  {
    initDependencies();
    Undertow server = buildServer();
    server.start();
  }
}
