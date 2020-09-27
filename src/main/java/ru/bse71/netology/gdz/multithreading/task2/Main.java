package ru.bse71.netology.gdz.multithreading.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static long THREAD_PERIOD = 3000;

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    List<Callable<Integer>> taskList = Arrays.asList(
        new MyCallable(4),
        new MyCallable(5),
        new MyCallable(3),
        new MyCallable(10)
    );

    final ExecutorService threadPool = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );

    final Integer msgCount = threadPool.invokeAny(taskList);
    threadPool.shutdown();

    System.out.println("Результат вызова одной из операций: " + msgCount);

  }
}
