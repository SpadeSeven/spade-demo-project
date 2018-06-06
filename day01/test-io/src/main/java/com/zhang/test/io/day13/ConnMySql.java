package com.zhang.test.java.day13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Administrator on 2018/6/4 0004.
 */
public class ConnMySql {

  public static void main(String[] args) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    Connection connection = null;
    Statement statement = null;
    try {
      connection = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
      statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from student");
      System.out.println(resultSet.getMetaData().getColumnCount());
      while (resultSet.next()) {
        System.out.println(resultSet.getString(1));
      }
    } finally {
      statement.close();
      connection.close();
    }
  }
}
