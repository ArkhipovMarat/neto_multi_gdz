package ru.bse71.netology.gdz.params.task1;

class Main {

  public static void main(String[] args) {
    BoxToy toy = new BoxToy();
    toy.start();
    UserThread user = new UserThread(toy);

    user.start();
  }
}
