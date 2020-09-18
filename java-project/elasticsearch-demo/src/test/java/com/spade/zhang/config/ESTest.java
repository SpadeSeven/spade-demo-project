package com.spade.zhang.config;

import com.spade.zhang.BaseTest;
import java.io.IOException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ESTest extends BaseTest {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @Test
  public void test_0() throws IOException {
    GetRequest getRequest = new GetRequest();
    getRequest.index("js_gps").id("1");
    GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
    System.out.println("end");
  }

}
