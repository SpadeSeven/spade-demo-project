package com.spade.spring.no1.no1_1;

import com.spade.spring.no1.no1_1.di.BraveKnight;
import com.spade.spring.no1.no1_1.di.Quest;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class BraveKnightTest {

  @Test
  public void knightShouldEmbarkOnQuest() {
    Quest mockQuest = mock(Quest.class);
    BraveKnight knight = new BraveKnight(mockQuest);
    knight.embarkOnQuest();
    // 验证 embark仅仅被调用一次
    verify(mockQuest, times(1)).embark();
  }
}
