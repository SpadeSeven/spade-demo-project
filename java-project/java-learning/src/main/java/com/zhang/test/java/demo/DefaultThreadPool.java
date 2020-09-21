package com.zhang.test.java.demo;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool {

  private static final int MAX_WORKER_NUMBERS = 10;

  private static final int DEFAULT_WORKER_NUMBERS = 10;

  private static final int MIN_WORKER_NUMBERS = 1;

  private final LinkedList<Job> jobs = new LinkedList<>();

  private final List<Worker> workers = Collections.synchronizedList(new ArrayList<>());

  private int workerNum = DEFAULT_WORKER_NUMBERS;

  private AtomicLong threadNum = new AtomicLong();

  public DefaultThreadPool() {
    initalizeWorkers(DEFAULT_WORKER_NUMBERS);
  }

  public DefaultThreadPool(int nums) {
    workerNum = Math.min(MAX_WORKER_NUMBERS, Math.max(nums, MIN_WORKER_NUMBERS));
    initalizeWorkers(nums);
  }

  private void execute(Job job) {
    if (job != null) {
      synchronized (jobs) {
        jobs.addLast(job);
        jobs.notify();
      }
    }
  }

  public void addWorkers(int num) {
    synchronized (jobs) {
      if (num + this.threadNum.intValue() > MAX_WORKER_NUMBERS) {
        num = MAX_WORKER_NUMBERS - this.threadNum.intValue();
      }
      initalizeWorkers(num);
      this.workerNum += num;
    }
  }

  public void removeWorker(int num) {
    synchronized (jobs) {
      if (num > this.workerNum) {
        throw new IllegalArgumentException("beyond worknum");
      }

      int count = 0;
      while (count < num) {
        Worker worker = workers.get(count);
        if (workers.remove(worker)) {
          worker.shutdown();
          count++;
        }
      }
      this.workerNum -= count;
    }
  }

  private void shutdown() {
    for (Worker worker : workers) {
      worker.shutdown();
    }
  }

  public int getJobSize() {
    return jobs.size();
  }

  public void initalizeWorkers(int nums) {
    for (int index = 0; index < nums; index++) {
      Worker worker = new Worker();
      workers.add(worker);
      Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
      thread.start();
    }
  }

  @Override
  public WorkQueue getAnyWorkQueue() {
    return null;
  }

  @Override
  public WorkQueue getWorkQueue(int queueId) throws NoSuchWorkQueueException {
    return null;
  }

  @Override
  public int numberOfWorkQueues() {
    return 0;
  }

  @Override
  public int minimumNumberOfThreads() {
    return 0;
  }

  @Override
  public int maximumNumberOfThreads() {
    return 0;
  }

  @Override
  public long idleTimeoutForThreads() {
    return 0;
  }

  @Override
  public int currentNumberOfThreads() {
    return 0;
  }

  @Override
  public int numberOfAvailableThreads() {
    return 0;
  }

  @Override
  public int numberOfBusyThreads() {
    return 0;
  }

  @Override
  public long currentProcessedCount() {
    return 0;
  }

  @Override
  public long averageWorkCompletionTime() {
    return 0;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public void close() throws IOException {

  }

  class Worker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {
      while (running) {
        Job job = null;
        synchronized (jobs) {
          while (jobs.isEmpty()) {
            try {
              jobs.wait();
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              return;
            }

            job = jobs.removeFirst();
          }

          if (job != null) {
            try {
              job.run();
            } catch (Exception e) {
              //
            }
          }
        }
      }
    }

    private void shutdown() {
      running = false;
    }
  }
}
