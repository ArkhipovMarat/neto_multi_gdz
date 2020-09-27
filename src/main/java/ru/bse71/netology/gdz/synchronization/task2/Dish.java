package ru.bse71.netology.gdz.synchronization.task2;

class Dish {
  private final Order order;

  Dish(Order order) {
    this.order = order;
  }

  Order getOrder() {
    return order;
  }
}
