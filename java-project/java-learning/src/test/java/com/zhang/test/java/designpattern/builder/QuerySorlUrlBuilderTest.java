package com.zhang.test.java.designpattern.builder;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class QuerySorlUrlBuilderTest {

  @Test
  public void createSorlUrl() {

    String url =
        QuerySorlUrlBuilder.createSorlUrl()
            .withSolrAdress("http://192.168.130.107:8983")
            .withCollection("trek_search_collection")
            .withQueryCondition("taskid:adhad")
            .withMaxDocNum(10000)
            .withSortRule("msis desc")
            .build();
    Assertions.assertThat(url)
        .isEqualTo(
            "http://192.168.130.107:8983/solr/trek_search_collection/select?&q=taskid:adhad&sort=msis desc&max_doc_num=10000");
  }
}
