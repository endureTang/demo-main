package com.api.test.extend;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-11 09:13
 **/
public class Circle extends Shape {

    public String area;

    public Circle() {
        System.out.println(super.area);
        area = "circleArea";
    }

    public void calcArea(){
        System.out.println("circle calcArea");
    }

    public static void main(String[] args) {
        Circle shape = new Circle();
        shape.calcArea();

        System.out.println(shape.area);
    }
}
