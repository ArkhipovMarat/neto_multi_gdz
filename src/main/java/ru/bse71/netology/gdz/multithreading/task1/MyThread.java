package ru.bse71.netology.gdz.multithreading.task1;

import static ru.bse71.netology.gdz.multithreading.task2.Main.THREAD_PERIOD;

class MyThread extends Thread {

  public MyThread(ThreadGroup group, String name) {
    super(group, name);
  }

  @Override
  public void run() {
    try {
      while (!isInterrupted()) {
        System.out.printf("Я %s. Всем привет!\n", getName());
        Thread.sleep(THREAD_PERIOD);
      }
    } catch (InterruptedException e) {

    } finally {
      System.out.printf("%s завершен\n", getName());
    }
  }
}
