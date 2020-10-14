package com.api.test.extend;

/**
 * @description:
 * @author: endure
 * @create: 2020-06-11 09:12
 **/
public class Shape {
    public String area;

    public Shape() {
        this.area = "shapeArea";
    }

    public void calcArea(){
        System.out.println("Shape calcArea");
    }
}
