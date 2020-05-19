package com.zhang.demo.part14;

import java.util.Arrays;
import java.util.List;

abstract class Shape {

  void draw() {
    System.out.println(this + ".draw()");
  }

  abstract public String toString();
}

class Circle extends Shape {

  public String toString() {
    return "Circle";
  }
}

class Square extends Shape {

  public String toString() {
    return "Square";
  }
}

class Triangle extends Shape {

  public String toString() {
    return "Triangle";
  }
}

class Rhomboid extends Shape {

  public String toString() {
    return "Rhomboid";
  }
}

public class Shapes {

  public static void main(String[] args) {
    List<Shape> shapes = Arrays.asList(new Circle(), new Square(), new Triangle());
    for (Shape shape : shapes) {
      shape.draw();
      rotate(shape);
    }

    Rhomboid rhomboid = new Rhomboid();
    Shape shape = rhomboid;
    if (shape instanceof Circle) {
      ((Circle) shape).draw();
    } else {
      System.out.println("shape can't convert to circle");
    }
  }

  public static void rotate(Shape s) {
    if (!(s instanceof Circle)) {
      System.out.println(s + " rotate");
    }
  }
}
