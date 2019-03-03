package com.zhang.demo.mytest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2019-03-03.
 */
public class TransientTest {

  public static void main(String[] args) {

    User user = new User();
    user.setUsername("spade");
    user.setPasswd("123456");

    System.out.println("read before Serializable: ");
    System.out.println("username: " + user.getUsername());
    System.err.println("password: " + user.getPasswd());

    try (ObjectOutputStream out = new ObjectOutputStream(
        new FileOutputStream("test-data/test.txt"))) {
      out.writeObject(user);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("test-data/test.txt"))) {
      User newUser = (User) in.readObject();
      System.out.println("read after Serializable: ");
      System.out.println("username: " + newUser.getUsername());
      System.err.println("password: " + newUser.getPasswd());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

}

class User implements Serializable {

  private static final long serialVersionUID = 8294180014912103005L;

  private String username;

  private transient String passwd;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
}
