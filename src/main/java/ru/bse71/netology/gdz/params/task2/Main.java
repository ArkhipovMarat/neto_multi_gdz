package ru.bse71.netology.gdz.params.task2;

import java.util.concurrent.atomic.LongAdder;

class Main {

  public static void main(String[] args) throws InterruptedException {
    LongAdder taxReport = new LongAdder();
    ShopThread thread1 = new ShopThread(taxReport);
    ShopThread thread2 = new ShopThread(taxReport);
    ShopThread thread3 = new ShopThread(taxReport);

    startAll(thread1, thread2, thread3);

    joinAll(thread1, thread2, thread3);

    System.out.println("Сумма всех: " + taxReport.sum());
  }

  private static void startAll(Thread ... threads) {
    for (Thread thread : threads) {
      thread.start();
    }
  }

  private static void joinAll(Thread ... threads) throws InterruptedException {
    for (Thread thread : threads) {
      thread.join();
    }
  }
}
