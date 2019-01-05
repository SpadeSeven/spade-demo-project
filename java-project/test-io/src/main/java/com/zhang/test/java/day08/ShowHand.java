package com.zhang.test.java.day08;

import com.google.common.base.Strings;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/3 0003.
 */
public class ShowHand {

  private final int PLAY_NUM = 5;

  private String[] types = {"方块", "草花", "红心", "黑桃"};
  private String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
  // 剩余的牌
  private List<String> cards = new LinkedList<String>();
  // 定义所有的玩家
  private String[] players = new String[PLAY_NUM];
  // 所有玩家手上的扑克牌
  private List<String>[] playersCards = new List[PLAY_NUM];

  /**
   * 初始化扑克牌，放入52张
   * 并且使用shuffle
   */
  public void initCards() {
    for (int i = 0; i < types.length; i++) {
      for (int j = 0; j < values.length; j++) {
        cards.add(types[i] + values[j]);
      }
    }
    Collections.shuffle(cards);
  }

  /**
   * 初始化玩家，为每个玩家分配用户名
   */
  public void initPlayer(String... names) {
    if (names.length > PLAY_NUM || names.length < 2) {
      System.out.println("玩家数量不对");
      return;
    } else {
      for (int i = 0; i < names.length; i++) {
        players[i] = names[i];
      }
    }
  }

  /**
   * 初始化玩家手中的牌
   */
  public void initPlayerCards() {
    for (int i = 0; i < players.length; i++) {
      if (!Strings.isNullOrEmpty(players[i])) {
        playersCards[i] = new LinkedList<String>();
      }
    }
  }

  /**
   * 输出全部牌
   */
  public void showAllCards() {
    for (String card : cards) {
      System.out.println(card);
    }
  }

  /**
   * 发牌
   *
   * @param first 最先发给谁
   */
  public void deliverCard(String first) {
    // 查出指定元素在数组中的索引
    int firstPos = ArrayUtils.search(players, first);
    for (int i = firstPos; i < PLAY_NUM; i++) {
      if (!Strings.isNullOrEmpty(players[i])) {
        playersCards[i].add(cards.get(0));
        cards.remove(0);
      }
    }

    // 依次给位于该指定玩家之前的每个玩家派牌
    for (int i = 0; i < firstPos; i++) {
      if (!Strings.isNullOrEmpty(players[i])) {
        playersCards[i].add(cards.get(0));
        cards.remove(0);
      }
    }
  }

  public void showPlayerCards() {
    for (int i = 0; i < PLAY_NUM; i++) {
      if (!Strings.isNullOrEmpty(players[i])) {
        System.out.println(players[i] + " : ");
        for (String card : playersCards[i]) {
          System.out.println(card);
        }
      }
    }
  }

  public static void main(String[] args) {
    ShowHand showHand = new ShowHand();
    showHand.initPlayer("电脑玩家","孙悟空");
    showHand.initCards();
    showHand.initPlayerCards();

    // 测试所有的牌
    showHand.showAllCards();
    System.out.println("===========");

    // 从孙悟空
    showHand.deliverCard("孙悟空");
    showHand.showPlayerCards();
    System.out.println("----------");
    // 再次从电脑玩家开始派牌
    showHand.deliverCard("电脑玩家");
    showHand.showPlayerCards();
  }
}
