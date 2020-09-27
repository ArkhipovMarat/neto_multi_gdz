package ru.bse71.netology.gdz.synchronization.task2;

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
        final Order order = restaurant.getOrderFromReadyVisitor();

        if (order != null) {
          restaurant.getKitchen().addOrderAndNotifyCook(order);

        } else {
          Dish dish = restaurant.getKitchen().getDish();
          if (dish != null) {
            System.out.printf("%s отнес заказ %s\n", Thread.currentThread().getName(), dish.getOrder().getVisitor().getName());
            dish.getOrder().getVisitor().receiveDish(dish);
          }
        }
      } catch (InterruptedException e) {}
    }
  }
}
