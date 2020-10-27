package com.spade.spring.no1.no1_1.di;

import java.io.PrintStream;

public class SlayDragonQuest implements Quest {

  private PrintStream stream;

  public SlayDragonQuest(PrintStream stream) {
    this.stream = stream;
  }

  @Override
  public void embark() {
    stream.println("embarking on quest to slay the dragon!");
  }
}
