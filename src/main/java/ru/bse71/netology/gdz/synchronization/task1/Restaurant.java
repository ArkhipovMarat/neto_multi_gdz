package ru.bse71.netology.gdz.synchronization.task1;

import static ru.bse71.netology.gdz.synchronization.task1.VisitorStatus.READY_FOR_ORDER;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Restaurant {

  private static final long COOK_TIME = 3000;

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

  public synchronized boolean isOrdersExist() {
    return !orders.isEmpty();
  }

  public synchronized VisitorThread getReadyVisitor() {
    for (VisitorThread visitor : visitors) {
      if (READY_FOR_ORDER.equals(visitor.getStatus())) return visitor;
    }
    return null;
  }

  public synchronized void notifyGarcon() {
    notify();
  }

  public synchronized void garconWork() throws InterruptedException {
    final VisitorThread readyVisitor = getReadyVisitor();

    if (readyVisitor == null) {
      wait();
    } else {
      System.out.printf("%s принял заказ у %s\n", Thread.currentThread().getName(), readyVisitor.getName());
      final Order order = readyVisitor.getOrder();
      Thread.sleep(COOK_TIME);

      System.out.printf("%s отнес заказ %s\n", Thread.currentThread().getName(), readyVisitor.getName());
      readyVisitor.receiveDish(new Dish(order));
    }
  }
}
