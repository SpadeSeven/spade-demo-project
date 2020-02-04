package com.spade.demo.part2;

/**
 * VM Args: -Xss128K
 */
public class JavaVMStackSOF {

  private int stackLength = 1;

  private void stackLeak() {
    stackLength++;
    stackLeak();
  }

  public static void main(String[] args) throws Throwable {
    JavaVMStackSOF oom = new JavaVMStackSOF();

    try {
      oom.stackLeak();
    } catch (Throwable e) {
      System.out.println("stack length: " + oom.stackLength);
      throw e;
    }
  }

}
