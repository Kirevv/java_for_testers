package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(8.0));
        Square.printSquareArea(new Square(10.0));

        Rectangle.printRectangleArea(new Rectangle(3.0, 5.0));
        Rectangle.printRectangleArea(new Rectangle(7.0, 9.0));

        Triangle.printTrianglePerimeter(new Triangle(1.0, 2.0, 3.0));
        Triangle.printTriangleArea(new Triangle(5., 6., 7.));

    }

}
