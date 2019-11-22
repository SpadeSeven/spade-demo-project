package com.zhang.demo.warmup.before;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class EggDropTest {

  private EggDrop drop;

  @Before
  public void before() {
    drop = new EggDrop();
  }

  @Ignore
  public void test() {
    Assertions.assertThat(drop.superEggDrop(1, 2)).isEqualTo(2);
    Assertions.assertThat(drop.superEggDrop(2, 6)).isEqualTo(3);
    Assertions.assertThat(drop.superEggDrop(3, 14)).isEqualTo(4);
  }
}
