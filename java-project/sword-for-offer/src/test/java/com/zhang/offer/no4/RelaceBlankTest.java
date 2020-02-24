package com.zhang.offer.no4;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class RelaceBlankTest {

  private RelaceBlank relacer;

  @Before
  public void before() {
    relacer = new RelaceBlank();
  }

  @Test
  public void test() {
    // 字符串在最前面
    Assertions.assertThat(relacer.relaceBlank(" wearehappy")).isEqualTo("%20wearehappy");
    // 字符串在中间
    Assertions.assertThat(relacer.relaceBlank("we are happy")).isEqualTo("we%20are%20happy");
    // 字符串在最后面
    Assertions.assertThat(relacer.relaceBlank("wearehappy ")).isEqualTo("wearehappy%20");
    // 连续空格
    Assertions.assertThat(relacer.relaceBlank("we  are  happy"))
        .isEqualTo("we%20%20are%20%20happy");
    // 没有空格
    Assertions.assertThat(relacer.relaceBlank("wearehappy")).isEqualTo("wearehappy");
    // null
    Assertions.assertThat(relacer.relaceBlank(null)).isEqualTo(null);
    // 空字符串
    Assertions.assertThat(relacer.relaceBlank("")).isEqualTo("");
    // 只有一个空字符串
    Assertions.assertThat(relacer.relaceBlank(" ")).isEqualTo("%20");
    // 只有空的连续多个字符串
    Assertions.assertThat(relacer.relaceBlank("  ")).isEqualTo("%20%20");
  }
}
