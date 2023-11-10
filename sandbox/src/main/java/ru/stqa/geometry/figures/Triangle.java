package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {
   public static void printTrianglePerimeter(double side1, double side2, double side3) {
       String text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f = %.2f", side1, side2, side3, trianglePerimeter(side1, side2, side3));
       System.out.println(text);
    }

    public static double trianglePerimeter(double side1, double side2, double side3) {
       return side1 + side2 + side3;
    }

    public static void printTriangleArea(double side1, double side2, double side3) {
        String text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f = %.2f", side1, side2, side3, triangleArea(side1, side2, side3));
        System.out.println(text);
    }

    public static double triangleArea(double side1, double side2, double side3) {
       var p = (side1 + side2 + side3) / 2;
       return sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }
}
