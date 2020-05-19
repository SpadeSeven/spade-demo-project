package com.zhang.demo.part14;

import java.util.Arrays;
import java.util.List;

abstract class Shape {

  boolean flag = false;

  void draw() {
    System.out.println(this + ".draw()");
  }

  abstract public String toString();
}

class Circle extends Shape {

  public String toString() {
    return (flag ? "H" : "Unh") + "ighlighted " + "Circle";
  }
}

class Square extends Shape {

  public String toString() {
    return (flag ? "H" : "Unh") + "ighlighted " + "Square";
  }
}

class Triangle extends Shape {

  public String toString() {
    return (flag ? "H" : "Unh") + "ighlighted " + "Triangle";
  }
}

class Rhomboid extends Shape {

  public String toString() {
    return (flag ? "H" : "Unh") + "ighlighted " + "Rhomboid";
  }
}

public class Shapes {

  public static void main(String[] args) {
    List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle());
    for (Shape shape : shapes) {
      setFlag(shape);
      shape.draw();
    }


  }

  public static void rotate(Shape s) {
    if (!(s instanceof Circle)) {
      System.out.println(s + " rotate");
    }
  }

  public static void setFlag(Shape s) {
    if (s instanceof Triangle) {
      ((Triangle) s).flag = true;
    }
  }
}
