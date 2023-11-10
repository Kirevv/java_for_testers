package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

   public static void printTrianglePerimeter(Triangle p) {
       var text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f = %.2f", p.side1, p.side2, p.side3, p.perimeter());
       System.out.println(text);
    }

    public double perimeter() {
       return this.side1 + this.side2 + this.side3;
    }

    public static void printTriangleArea(Triangle s) {
        var text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f = %.2f", s.side1, s.side2, s.side3, s.area());
        System.out.println(text);
    }

    public double area() {
       var p = (this.side1 + this.side2 + this.side3) / 2;
       return sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }
}
