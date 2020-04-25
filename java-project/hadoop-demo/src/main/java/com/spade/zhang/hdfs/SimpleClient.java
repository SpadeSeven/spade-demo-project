package com.spade.zhang.hdfs;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class SimpleClient {

  public static void main(String[] args) throws IOException {

    System.setProperty("HADOOP_USER_NAME", "hdfs");

    SimpleClient client = new SimpleClient();
    Path path = new Path("hdfs://master.ambari.bd.com:8020/hadoop-demo/hdfs-demo/task_1/");
    client.mkdir(path);

  }

  private void mkdir(Path path) throws IOException {
    Configuration config = new Configuration();
    config.addResource(
        "D:/code/my_code/spade-demo-project/java-project/hadoop-demo/conf/hdfs/core-site.xml");
    config.addResource(
        "D:/code/my_code/spade-demo-project/java-project/hadoop-demo/conf/hdfs/hdfs-site.xml");
    config.set("fs.default.name", "hdfs://master.ambari.bd.com:8020");
    FileSystem fileSystem = FileSystem.get(config);
    try {
      fileSystem.mkdirs(path);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      fileSystem.close();
    }

  }

}
