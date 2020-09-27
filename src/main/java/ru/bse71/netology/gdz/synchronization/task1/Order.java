package ru.bse71.netology.gdz.synchronization.task1;

class Order {
  private final VisitorThread visitor;

  public Order(VisitorThread visitor) {
    this.visitor = visitor;
  }

  public VisitorThread getVisitor() {
    return visitor;
  }
}
