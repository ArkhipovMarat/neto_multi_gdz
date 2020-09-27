package ru.bse71.netology.gdz.params.task1;

class UserThread extends Thread {

  private static final int WORK_COUNT = 5;
  private static final long SWITCH_ON_PERIOD = 2000;

  private BoxToy toy;

  public UserThread(BoxToy toy) {
    this.toy = toy;
  }

  @Override
  public void run() {
    for (int i = 0; i < WORK_COUNT; i++) {
      try {
        sleep(SWITCH_ON_PERIOD);
        System.out.println("Включаю тумблер!");
        toy.switchToggle(true);
      } catch (InterruptedException e) { }
    }
  }
}
