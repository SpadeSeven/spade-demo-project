package com.zhang.demo.warmup.simulate;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class HappyNumTest {


  private HappyNum happyNum;

  @Before
  public void before() {
    happyNum = new HappyNum();
  }

  @Test
  public void test_happyNUm() {

    Assertions.assertThat(happyNum.isHappy(19)).isTrue();
    Assertions.assertThat(happyNum.isHappy(1)).isTrue();
    Assertions.assertThat(happyNum.isHappy(2)).isFalse();
  }

  @Test
  public void test_calcHappyNum() {
    Assertions.assertThat(happyNum.calcHappyNum(1)).isEqualTo(1);
  }

}
