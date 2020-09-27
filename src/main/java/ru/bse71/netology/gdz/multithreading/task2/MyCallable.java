package ru.bse71.netology.gdz.multithreading.task2;

import static ru.bse71.netology.gdz.multithreading.task2.Main.THREAD_PERIOD;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
  int iterCount;

  public MyCallable(int iterCount) {
    this.iterCount = iterCount;
  }

  @Override
  public Integer call() {
    int count = 0;
    try {
      for(int i = iterCount; i > 0; i--) {
        System.out.println("Всем привет!");
        count++;
        Thread.sleep(THREAD_PERIOD);
      }
    } catch (InterruptedException e) {

    } finally {
      System.out.println("Задача завершена!");
    }
    return count;
  }
}
