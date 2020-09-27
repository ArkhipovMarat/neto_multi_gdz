package ru.bse71.netology.gdz.multithreading.task3;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

class Main {

  private static final int ARRAY_SIZE = 10000;

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    long startOperation;
    int calculateResult;
    final ForkJoinPool forkJoinPool = new ForkJoinPool(
        Runtime.getRuntime().availableProcessors());
    int[] targetArray = generateArray();

    startOperation = System.currentTimeMillis();
    calculateResult = calculate(targetArray);
    System.out.printf("Время однопоточного рассчета: %d. Результат: %d\n",
        System.currentTimeMillis() - startOperation,
        calculateResult);

    startOperation = System.currentTimeMillis();
    calculateResult = parallelCalculate(targetArray, forkJoinPool);
    System.out.printf("Время многопоточного рассчета: %d. Результат: %d\n",
        System.currentTimeMillis() - startOperation,
        calculateResult);
  }

  private static int[] generateArray() {
    Random rand = new Random();
    int[] array = new int[ARRAY_SIZE];
    for (int i = 0; i < ARRAY_SIZE; i++) {
      array[i] = rand.nextInt();
    }
    return array;
  }

  private static int calculate(int[] array) {
    int sum = 0;
    for (int i : array) {
      sum += i;
    }
    return sum;
  }

  private static int parallelCalculate(int[] array, ForkJoinPool forkJoinPool)
      throws ExecutionException, InterruptedException {
    return forkJoinPool.submit(
        new CalculateArrayTask(
            array, 0, array.length
        )
    ).get();
  }

}
