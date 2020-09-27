package ru.bse71.netology.gdz.synchronization.task2;

public class Main {

  private static final int VISITOR_COUNT = 5;
  private static final long VISITOR_PERIOD = 2000;

  public static void main(String[] args) {
    Restaurant restaurant = new Restaurant();

    addGarcon("Володя", restaurant);
    addGarcon("Иван", restaurant);
    addGarcon("Петр", restaurant);
    addCook("Андрей", restaurant);

    addVisitorsAndExit(restaurant);
  }

  private static void addCook(String name, Restaurant restaurant) {
    final CookThread cookThread = new CookThread("Повар " + name, restaurant);
    cookThread.setDaemon(true);
    cookThread.start();
  }

  private static void addGarcon(String name, Restaurant restaurant) {
    final GarconThread garconThread = new GarconThread("Официант " + name, restaurant);
    garconThread.setDaemon(true);
    garconThread.start();
  }

  private static void addVisitorsAndExit(Restaurant restaurant) {
    Thread[] visitors = new Thread[VISITOR_COUNT];
    for (int i = 0; i < VISITOR_COUNT; i++) {
      visitors[i] = new VisitorThread("Посетитель " + i, restaurant);
      visitors[i].start();
      try {
        Thread.sleep(VISITOR_PERIOD);
      } catch (InterruptedException e) {}
    }
    for (int i = 0; i < VISITOR_COUNT; i++) {
      try {
        visitors[i].join();
      } catch (InterruptedException e) {}
    }
  }

}
