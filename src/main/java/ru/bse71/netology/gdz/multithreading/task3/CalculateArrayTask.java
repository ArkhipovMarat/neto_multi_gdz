package ru.bse71.netology.gdz.multithreading.task3;

import java.util.concurrent.RecursiveTask;

public class CalculateArrayTask extends RecursiveTask<Integer> {

  int[] targetArray;
  int startPosition;
  int endPosition;

  public CalculateArrayTask(int[] targetArray, int startPosition, int endPosition) {
    this.targetArray = targetArray;
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  @Override
  protected Integer compute() {
    switch (endPosition - startPosition) {
      case 0: return 0;
      case 1: return targetArray[startPosition];
      case 2: return targetArray[startPosition] + targetArray[startPosition + 1];
      default:
              return separateTaskAndGetResult();
    }
  }

  private Integer separateTaskAndGetResult() {
    int middle = (endPosition - startPosition) / 2 + startPosition;
    RecursiveTask<Integer> fork1 = new CalculateArrayTask(targetArray, startPosition, middle);
    RecursiveTask<Integer> fork2 = new CalculateArrayTask(targetArray, middle, endPosition);

    fork1.fork();
    fork2.fork();

    return fork1.join() + fork2.join();
  }
}
