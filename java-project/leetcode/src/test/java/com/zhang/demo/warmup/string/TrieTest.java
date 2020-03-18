package com.zhang.demo.warmup.string;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TrieTest {

  @Test
  public void test() {
    Trie trie = new Trie();
    trie.insert("apple");
    Assertions.assertThat(trie.search("apple")).isTrue(); // 返回 true
    Assertions.assertThat(trie.search("app")).isFalse();
    Assertions.assertThat(trie.startsWith("app")).isTrue();
    trie.insert("app");
    Assertions.assertThat(trie.search("app")).isTrue();
  }
}
