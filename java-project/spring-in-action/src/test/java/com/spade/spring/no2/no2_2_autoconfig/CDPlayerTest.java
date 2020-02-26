package com.spade.spring.no2.no2_2_autoconfig;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

  @Rule public final SystemOutRule rule = new SystemOutRule().enableLog();

  @Autowired private MediaPlayer player;

  @Autowired private CompactDisc cd;

  @Test
  public void cdShouldNotBeNull() {
    Assertions.assertThat(cd).isNotNull();
  }

  @Test
  public void test_play() {
    player.play();
    Assertions.assertThat(rule.getLog())
        .isEqualTo(
            "Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles"
                + System.lineSeparator());
  }
}
