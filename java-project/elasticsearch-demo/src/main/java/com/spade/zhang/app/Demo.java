package com.spade.zhang.app;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class Demo {

  public static void main(String[] args) throws IOException {
    // 阿里云Elasticsearch集群需要basic auth验证。
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    //访问用户名和密码为您创建阿里云Elasticsearch实例时设置的用户名和密码，也是Kibana控制台的登录用户名和密码。
    credentialsProvider.setCredentials(
        AuthScope.ANY, new UsernamePasswordCredentials("elastic", "spade123!@#"));

    // 通过builder创建rest client，配置http client的HttpClientConfigCallback。
    // 单击所创建的Elasticsearch实例ID，在基本信息页面获取公网地址，即为ES集群地址。
    RestClientBuilder builder = RestClient
        .builder(new HttpHost("es-cn-m7r1qhg1c0003lg3p.public.elasticsearch.aliyuncs.com", 9200))
        .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
          @Override
          public HttpAsyncClientBuilder customizeHttpClient(
              HttpAsyncClientBuilder httpClientBuilder) {
            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
          }
        });

    // RestHighLevelClient实例通过REST low-level client builder进行构造。
    RestHighLevelClient highClient = new RestHighLevelClient(builder);

    try {
      // 创建request。
      Map<String, Object> jsonMap = new HashMap<>();
      // field_01、field_02为字段名，value_01、value_02为对应的值。
      jsonMap.put("test", "test_value_0");
      //index_name为索引名称；type_name为类型名称；doc_id为文档的id。
      IndexRequest indexRequest = new IndexRequest("test", "_doc", "1").source(jsonMap);

      // 同步执行。
      IndexResponse indexResponse = highClient.index(indexRequest);

      long version = indexResponse.getVersion();

      System.out.println("Index document successfully! " + version);
      //index_name为索引名称；type_name为类型名称；doc_id为文档的id。与以上创建索引的名称和id相同。
//      DeleteRequest request = new DeleteRequest("{index_name}", "{type_name}", "{doc_id}");
//      DeleteResponse deleteResponse = highClient.delete(request);
//
//      System.out.println("Delete document successfully!");

    } catch (IOException ioException) {
      // 异常处理。
      System.out.println("error");
      System.out.println(ioException.getMessage());
    } finally {
      highClient.close();
    }
  }
}
