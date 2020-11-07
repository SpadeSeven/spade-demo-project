package com.zhang.demo.algorithms.string.easy;

import com.zhang.demo.algorithms.string.medium.No151ReverseWords;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class No151ReverseWordsTest {

  @Test
  public void test_ReverseWords() {
    No151ReverseWords reverser = new No151ReverseWords();
    Assertions.assertThat(reverser.reverseWords("the sky is blue")).isEqualTo("blue is sky the");
    Assertions.assertThat(reverser.reverseWords("  hello world!  ")).isEqualTo("world! hello");
    Assertions.assertThat(reverser.reverseWords("a good   example")).isEqualTo("example good a");
    Assertions.assertThat(reverser.reverseWords("  Bob    Loves  Alice   "))
        .isEqualTo("Alice Loves Bob");
    Assertions.assertThat(reverser.reverseWords("Alice does not even like bob"))
        .isEqualTo("bob like even not does Alice");
  }
}
