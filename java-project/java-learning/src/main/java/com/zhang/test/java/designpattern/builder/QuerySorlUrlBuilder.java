package com.zhang.test.java.designpattern.builder;

public final class QuerySorlUrlBuilder {

  private static final String QUERY_SOLR_URL_TEMPLATE =
      "%s/solr/%s/select?&q=%s&sort=%s&max_doc_num=%d";

  private String solrAdress;
  private String collection;
  private String queryCondition;
  private String sortRule;
  private int maxDocNum;

  private QuerySorlUrlBuilder() {}

  public static QuerySorlUrlBuilder createSorlUrl() {
    return new QuerySorlUrlBuilder();
  }

  public QuerySorlUrlBuilder withSolrAdress(String solrAdress) {
    this.solrAdress = solrAdress;
    return this;
  }

  public QuerySorlUrlBuilder withCollection(String collection) {
    this.collection = collection;
    return this;
  }

  public QuerySorlUrlBuilder withQueryCondition(String queryCondition) {
    this.queryCondition = queryCondition;
    return this;
  }

  public QuerySorlUrlBuilder withSortRule(String sortRule) {
    this.sortRule = sortRule;
    return this;
  }

  public QuerySorlUrlBuilder withMaxDocNum(int maxDocNum) {
    this.maxDocNum = maxDocNum;
    return this;
  }

  public String build() {

    return String.format(
        QUERY_SOLR_URL_TEMPLATE, solrAdress, collection, queryCondition, sortRule, maxDocNum);
  }
}
