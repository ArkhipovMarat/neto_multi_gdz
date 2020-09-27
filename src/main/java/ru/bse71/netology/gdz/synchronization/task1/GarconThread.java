package ru.bse71.netology.gdz.synchronization.task1;

class GarconThread extends Thread {

  Restaurant restaurant;

  public GarconThread(String name, Restaurant restaurant) {
    super(name);
    this.restaurant = restaurant;
  }

  @Override
  public void run() {
    while (true) {
      try {
        restaurant.garconWork();
      } catch (InterruptedException e) {}
    }
  }
}
