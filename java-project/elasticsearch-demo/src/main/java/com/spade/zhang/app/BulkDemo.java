package com.spade.zhang.app;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.DocWriteRequest.OpType;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class BulkDemo {

  public static void main(String[] args) throws IOException {
    // 阿里云Elasticsearch集群需要basic auth验证。
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    //访问用户名和密码为您创建阿里云Elasticsearch实例时设置的用户名和密码，也是Kibana控制台的登录用户名和密码。
    credentialsProvider.setCredentials(
        AuthScope.ANY, new UsernamePasswordCredentials("elastic", "Lzy@123456"));

    // 通过builder创建rest client，配置http client的HttpClientConfigCallback。
    // 单击所创建的Elasticsearch实例ID，在基本信息页面获取公网地址，即为ES集群地址。
    RestClientBuilder builder = RestClient
        .builder(new HttpHost("es-cn-m7r1qxlb0000i08b1.public.elasticsearch.aliyuncs.com", 9200))
        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
          @Override
          public HttpAsyncClientBuilder customizeHttpClient(
              HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
          }
        });

    // RestHighLevelClient实例通过REST low-level client builder进行构造。
    RestHighLevelClient highClient = new RestHighLevelClient(builder);

    Map<String, String> doc = Maps.newHashMap();
    doc.put("test_1", "value_1");
    IndexRequest request_1 = new IndexRequest("test","_doc","1").opType(OpType.CREATE).source(doc);
    IndexRequest request_2 = new IndexRequest("test","_doc","2").opType(OpType.CREATE).source(doc);
    IndexRequest request_3 = new IndexRequest("test","_doc","3").opType(OpType.CREATE).source(doc);
    IndexRequest request_4 = new IndexRequest("test","_doc","4").opType(OpType.CREATE).source(doc);
    IndexRequest request_5 = new IndexRequest("test","_doc","5").opType(OpType.CREATE).source(doc);
    IndexRequest request_6 = new IndexRequest("test","_doc","6").opType(OpType.CREATE).source(doc);
    IndexRequest request_7 = new IndexRequest("test","_doc","7").opType(OpType.CREATE).source(doc);

    BulkRequest bulkRequest = new BulkRequest();
    bulkRequest.add(request_1);
    bulkRequest.add(request_2);
    bulkRequest.add(request_3);
    bulkRequest.add(request_4);
    bulkRequest.add(request_5);
    bulkRequest.add(request_6);
    bulkRequest.add(request_7);

    try {
      BulkResponse responses = highClient.bulk(bulkRequest);
      System.out.println("responses.hasFailures(): " + responses.hasFailures() + responses.buildFailureMessage());
    } finally {
      highClient.close();
    }


  }
}
