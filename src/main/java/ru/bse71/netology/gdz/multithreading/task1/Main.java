package ru.bse71.netology.gdz.multithreading.task1;

public class Main {

  public static long THREAD_PERIOD = 3000;
  public static long THREAD_RUNNING_TIME = 15000;

  public static void main(String[] args) throws InterruptedException {

    ThreadGroup threadGroup = new ThreadGroup("myGroup");
    startThread(threadGroup, "Поток 1", "Поток 2", "Поток 3", "Поток 4");

    Thread.sleep(THREAD_RUNNING_TIME);

    threadGroup.interrupt();

  }

  public static void startThread(ThreadGroup threadGroup, String ... names) {
    for (String name : names) {
      new MyThread(threadGroup, name).start();
    }
  }
}
