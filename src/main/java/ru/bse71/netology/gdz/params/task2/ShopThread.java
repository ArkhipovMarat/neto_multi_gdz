package ru.bse71.netology.gdz.params.task2;

import java.util.Random;
import java.util.concurrent.atomic.LongAdder;

class ShopThread extends Thread {

  private static final int SELL_COUNT = 50000;
  private static final int MAX_SELL = 10000;

  private final LongAdder taxReport;

  public ShopThread(LongAdder taxReport) {
    this.taxReport = taxReport;
  }

  @Override
  public void run() {
    Random rand = new Random();
    for (int i = 0; i < SELL_COUNT; i++) {
      taxReport.add(Math.abs(rand.nextLong() % MAX_SELL));
    }
  }
}
