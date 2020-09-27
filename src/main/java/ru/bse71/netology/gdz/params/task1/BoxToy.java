package ru.bse71.netology.gdz.params.task1;

class BoxToy {
  private volatile boolean toggle;
  private BoxToyThread thread;

  public BoxToy() {
    this.thread = new BoxToyThread();
  }

  public void start() {
    System.out.println("Батарейки вставлены. СТАРТ!");
    thread.setDaemon(true);
    thread.start();
  }

  public void switchToggle(boolean position) {
    System.out.println(position ? "ВКЛЮЧЕНО!" : "ВЫКЛЮЧЕНО!");
    toggle = position;
  }

  public class BoxToyThread extends Thread {

    @Override
    public void run() {
      while(!isInterrupted()) {
        if (toggle) {
          switchToggle(false);
        }
      }
    }
  }
}
