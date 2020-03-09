package com.zhang.test.java.designpattern.chain;

public abstract class Handler {

  /** 持有后继的责任对象 */
  protected Handler successor;

  public abstract void handleRequest();

  public Handler getSuccessor() {
    return successor;
  }

  public void setSuccessor(Handler successor) {
    this.successor = successor;
  }
}
