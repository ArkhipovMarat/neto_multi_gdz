package ru.bse71.netology.gdz.synchronization.task2;

public class CookThread extends Thread {

  private static final int COOK_TIME = 3000;

  private final Restaurant restaurant;

  public CookThread(String name, Restaurant restaurant) {
    super(name);
    this.restaurant = restaurant;
  }

  @Override
  public void run() {
    while (true) {
      try {
        //  Повар постоянно готовит заказы и отдает блюдо
        Order order = restaurant.getKitchen().waitOrder();
        System.out.printf("Я %s и я начал готовить заказ для %s\n", getName(), order.getVisitor().getName());
        Thread.sleep(COOK_TIME);

        System.out.printf("Я %s и я приготовил блюдо для %s\n", getName(), order.getVisitor().getName());
        restaurant.getKitchen().addDish(new Dish(order));
      } catch (InterruptedException e) {}
    }
  }
}
