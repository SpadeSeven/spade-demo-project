package com.spade.spring.no2.no2_2_autoconfig;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CompactDiscTest {

  @Autowired private CompactDisc cd;

  @Test
  public void cdShouldNotBeNull() {
    Assertions.assertThat(cd).isNotNull();
  }
}
