package ru.bse71.netology.gdz.synchronization.task1;

import static ru.bse71.netology.gdz.synchronization.task1.VisitorStatus.*;

import java.util.Objects;

public class VisitorThread extends Thread {

  private static final long ORDER_WAIT = 3000;
  private static final long EAT_WAIT = 3000;

  Restaurant restaurant;
  volatile VisitorStatus status;

  public VisitorThread(String name, Restaurant restaurant) {
    super(name);
    this.restaurant = restaurant;
    this.status = NEW;
  }

  @Override
  public void run() {
    //  Заходим в ресторан
    System.out.printf("%s пришел в ресторан\n", getName());
    restaurant.addVisitor(this);

    //  Читаем меню
    try {
      sleep(ORDER_WAIT);
    } catch (InterruptedException e) {}

    //  Делаем заказ
    order();

    //  Ждем, когда принесут заказ
    while (!READY_FOR_EAT.equals(status));

    //  Кушаем и уходим
    eat();
  }

  private void order() {
    //  Придумали, что заказать
    status = READY_FOR_ORDER;
    System.out.printf("%s готов сделать заказ\n", getName());

    //  Зовем свободного официанта
    restaurant.notifyGarcon();
  }

  private void eat() {
    status = EATING;
    System.out.printf("%s приступил к приему пищи\n", getName());
    try {
      sleep(EAT_WAIT);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("%s ушел\n", getName());
    restaurant.leave(this);
  }

  public void receiveDish(Dish dish) {
    status = READY_FOR_EAT;
    System.out.printf("%s получил свой заказ\n", getName());
  }

  public VisitorStatus getStatus() {
    return status;
  }

  public Order getOrder() {
    status = WAIT;
    System.out.printf("%s сделал заказ\n", getName());
    return new Order(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VisitorThread that = (VisitorThread) o;
    return getId() == that.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
