package ru.stqa.geometry.figures;

public class Rectangle {

    private double side1;
    private double side2;

    public Rectangle(double side1, double side2) {

        this.side1 = side1;
        this.side2 = side2;
    }
    public static void printRectangleArea(Rectangle s) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", s.side1, s.side2, s.rectangleArea());
        System.out.println(text);
    }

    public double rectangleArea() {

        return this.side1 * this.side2;
    }
}
