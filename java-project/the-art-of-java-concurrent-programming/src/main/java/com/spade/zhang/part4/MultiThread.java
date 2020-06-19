package com.spade.zhang.part4;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 93908
 */
public class MultiThread {

  public static void main(String[] args) {
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
    for (ThreadInfo threadInfo : threadInfos) {
      System.out
          .println(String.format("[%s] %s", threadInfo.getThreadId(), threadInfo.getThreadName()));
    }
  }
}
