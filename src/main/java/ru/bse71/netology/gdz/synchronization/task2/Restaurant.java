package ru.bse71.netology.gdz.synchronization.task2;

import static ru.bse71.netology.gdz.synchronization.task2.VisitorStatus.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Restaurant {

  private static final long COOK_TIME = 3000;

  private final Kitchen kitchen = new Kitchen();
  private final Queue<Order> orders = new LinkedList<>();
  private final Set<VisitorThread> visitors = new HashSet<>();

  public synchronized void addOrder(Order order) {
    orders.add(order);
  }

  public synchronized void addVisitor(VisitorThread visitor) {
    visitors.add(visitor);
  }

  public synchronized void leave(VisitorThread visitorThread) {
    visitors.remove(visitorThread);
  }

  public boolean isEmpty() {
    return visitors.size() == 0;
  }

  public synchronized boolean isOrdersExist() {
    return !orders.isEmpty();
  }

  public synchronized Order getOrderFromReadyVisitor() {
    VisitorThread targetVisitor = null;
    for (VisitorThread visitor : visitors) {
      if (READY_FOR_ORDER.equals(visitor.getStatus())) {
        targetVisitor = visitor;
        break;
      }
    }
    if (targetVisitor == null) return null;
    System.out.printf("%s принял заказ у %s\n", Thread.currentThread().getName(), targetVisitor.getName());
    return targetVisitor.getOrder();
  }

  public synchronized void notifyGarcon() {
    notify();
  }

  public Kitchen getKitchen() {
    return kitchen;
  }

  public static class Kitchen {

    final private Queue<Order> orders = new LinkedList<>();
    final private Queue<Dish> dishes = new LinkedList<>();

    public void addOrderAndNotifyCook(Order order) {
      synchronized (this) {
        orders.offer(order);
        notify();
      }
    }

    public Order waitOrder() throws InterruptedException {
      synchronized (this) {
        if (orders.isEmpty()) wait();
        return orders.poll();
      }
    }

    public synchronized void addDish(Dish dish) {
      dishes.offer(dish);
    }

    public synchronized Dish getDish() throws InterruptedException {
      return dishes.poll();
    }
  }
}
