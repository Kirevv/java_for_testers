package ru.stqa.geometry.figures;

public record Rectangle (double side1, double side2) {

    public Rectangle {
        if (side1 < 0 || side2 < 0) {
            throw new IllegalArgumentException("Rectangle side should be non-negative ");
        }
    }
    public static void printRectangleArea(Rectangle s) {
        var text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", s.side1, s.side2, s.rectangleArea());
        System.out.println(text);
    }

    public double rectangleArea() {

        return this.side1 * this.side2;
    }
}
